package vendingmachine.controller;

import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Change;
import vendingmachine.dto.Coins;
import vendingmachine.dto.Item;
import vendingmachine.service.VendingMachineInsufficientFundsException;
import vendingmachine.service.VendingMachineService;
import vendingmachine.ui.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineService service;


    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run()
    {
        BigDecimal balance = addFunds();
        boolean start = true;
        try {
            while (start) {
                printMenuShowBalance(balance);
                int operation = getMenuSelection();
                if (operation == 0) {
                    quit();
                    start = false;
                }
                else {
                    Item selectedItem = getItemSelected(operation - 1);  // item menu list starts at 1, Index at 0.
                    BigDecimal newBalance = vendItem(selectedItem, balance);
                    balance = newBalance;
                }
            }
        }
        catch(VendingMachinePersistenceException | VendingMachineInsufficientFundsException e)
        {
            view.displayBalance(balance);
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void printMenuShowBalance(BigDecimal bal) throws VendingMachinePersistenceException {
        view.printMenu();
        List<Item> itemList = service.listAllItems();
        view.printAllItems(itemList);
        view.displayBalance(bal);
    }

    private int getMenuSelection() {

        return view.getMenuSelection();
    }

    private void quit()
    {
        view.displayQuitMessage();
    }

    private BigDecimal addFunds() {
        BigDecimal funds = view.displayAndGetFunds();
        return funds;
    }

    private Item getItemSelected(int index) throws VendingMachinePersistenceException {
        return service.listAllItems().get(index);
    }

    private BigDecimal vendItem(Item item, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException {
        BigDecimal newBal = service.vendItem(balance, item);
        if(newBal.doubleValue() >= 0)
        {
            Change myChange = new Change();
            Map<Coins, Integer> changeMap = myChange.getChange(balance, item.getCost());
            view.displayChange(changeMap);
        }
        return newBal;
    }

}
