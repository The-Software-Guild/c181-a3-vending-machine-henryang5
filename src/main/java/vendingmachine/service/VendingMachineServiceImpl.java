package vendingmachine.service;

import vendingmachine.dao.VendingMachineAudioDao;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public List<Item> listAllItems() throws VendingMachinePersistenceException {
        return dao.listAllItems();
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
    public BigDecimal vendItem(BigDecimal totalFunds, Item item) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException {
        if(item.getCost().compareTo(totalFunds) > 0)
        {
            throw new VendingMachineInsufficientFundsException("Not enough funds in machine");
        }
        changeInventoryCount(item, item.getNumInventoryItems() - 1);

        auditDao.writeAuditEntry(
                "Item " + item.getName() + " vended." + "Number in Inventory: " + item.getNumInventoryItems());
        return totalFunds.subtract(item.getCost());
    }
}
