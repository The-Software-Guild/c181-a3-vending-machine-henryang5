package vendingmachine.service;

import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineService {

    Item getItem(String name) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException;
    List<Item> listAllItems() throws VendingMachinePersistenceException;
    Item addItem(Item item) throws VendingMachinePersistenceException;
    Item removeItem(Item item) throws VendingMachinePersistenceException;
    void changeInventoryCount(Item item, int newCount) throws VendingMachinePersistenceException;
    BigDecimal vendItem(BigDecimal totalFunds, Item item)
            throws VendingMachinePersistenceException,
            VendingMachineInsufficientFundsException,
            VendingMachineNoItemInventoryException;
}
