package vendingmachine.dao;

import vendingmachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {
    Item getItem(String name) throws VendingMachinePersistenceException;
    List<Item> listAllItems() throws VendingMachinePersistenceException;
    Item addItem(Item item) throws VendingMachinePersistenceException;
    Item removeItem(Item item) throws VendingMachinePersistenceException;
    void changeInventoryCount(Item item, int newCount) throws VendingMachinePersistenceException;
}
