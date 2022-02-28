package vendingmachine.service;

import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    public Item testItem;

    public VendingMachineDaoStubImpl()
    {
        testItem = new Item("Chips", new BigDecimal(2.99), 2);
    }

    public VendingMachineDaoStubImpl(Item testItem)
    {
        this.testItem = testItem;
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        if(name.equals(testItem.getName()))
        {
            return testItem;
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Item> listAllItems() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(testItem);
        return itemList;
    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public Item removeItem(Item item) throws VendingMachinePersistenceException {
        return null;
    }

    @Override
    public void changeInventoryCount(Item item, int newCount) throws VendingMachinePersistenceException {
        if(item != null) {
            item.setNumInventoryItems(newCount);
        }
    }
}
