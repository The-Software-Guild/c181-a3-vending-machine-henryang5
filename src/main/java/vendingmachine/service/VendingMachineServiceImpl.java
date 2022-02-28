package vendingmachine.service;

import vendingmachine.dao.VendingMachineAudioDao;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceImpl implements VendingMachineService {
    private VendingMachineDao dao;
    private VendingMachineAudioDao auditDao;

    public VendingMachineServiceImpl(VendingMachineDao dao, VendingMachineAudioDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        Item item = dao.getItem(name);
        if(item.getNumInventoryItems() == 0 || item == null)
        {
            throw new VendingMachineNoItemInventoryException("No item found with " + name);
        }
        return item;
    }


    // lists all items with inventory count > 0
    @Override
    public List<Item> listAllItems() throws VendingMachinePersistenceException {
        return dao.listAllItems()
                .stream()
                .filter(item -> item.getNumInventoryItems() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        return dao.addItem(item);
    }

    @Override
    public Item removeItem(Item item) throws VendingMachinePersistenceException {
        return dao.removeItem(item);
    }

    @Override
    public void changeInventoryCount(Item item, int newCount) throws VendingMachinePersistenceException {
        if(item == null) {
            throw new VendingMachinePersistenceException("Null item");
        }
        else {
            dao.changeInventoryCount(item, newCount);
        }
    }

    @Override
    public BigDecimal vendItem(BigDecimal totalFunds, Item item) throws VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        if(item.getCost().compareTo(totalFunds) > 0)
        {
            throw new VendingMachineInsufficientFundsException("Not enough funds in machine");

        }
        else if(item.getNumInventoryItems() <= 0)
        {
            throw new VendingMachineNoItemInventoryException("Item inventory is empty");
        }
        else {
            changeInventoryCount(item, item.getNumInventoryItems() - 1);
            auditDao.writeAuditEntry(
                    "Item " + item.getName() + " vended." + "Number in Inventory: " + item.getNumInventoryItems());
        }
        return totalFunds.subtract(item.getCost());
    }
}
