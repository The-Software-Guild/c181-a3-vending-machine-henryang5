package vendingmachine.controller;

import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Change;
import vendingmachine.dto.Coins;
import vendingmachine.dto.Item;
import vendingmachine.service.VendingMachineInsufficientFundsException;
import vendingmachine.service.VendingMachineNoItemInventoryException;
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
        BigDecimal balance = new BigDecimal(0.0);
        boolean start = true;
        try {
            while (start) {
                printMenu();
                showBalance(balance);
                String operation = getMenuSelection();
                switch(operation) {
                    case "q":  // quit
                        quit();
                        start = false;
                        break;
                    case "a":  // add funds
                        balance = addFunds(balance);
                        break;
                    case "b": // vend item
                        try {
                            BigDecimal newBal = buyItem(balance);
                            balance = newBal;
                        }
                        catch(VendingMachineInsufficientFundsException | VendingMachineNoItemInventoryException e)
                        {
                            view.displayBalance(balance);
                            view.displayErrorMessage(e.getMessage());
                        }
                        break;
                    default:
                        unknownCommand();
                }
            }
        }
        catch(VendingMachinePersistenceException e)
        {
            view.displayBalance(balance);
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void unknownCommand()
    {
        view.displayUnknownCommand();
    }
    private BigDecimal buyItem(BigDecimal balance) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        int selection;
        do {
            selection = view.getItemSelection();
        } while(!isValidItem(selection));

        Item selectedItem = getItemSelected(selection - 1);

        BigDecimal newBalance = vendItem(selectedItem, balance);

        return new BigDecimal(0 );
    }

    private boolean isValidItem(int selection) throws VendingMachinePersistenceException {
        if(selection > service.listAllItems().size() || selection < 1) {
            view.printInvalidItem();
            return false;
        }
        return true;
    }
    private void printMenu() throws VendingMachinePersistenceException {
        List<Item> itemList = service.listAllItems();
        view.printAllItems(itemList);
        view.printMenu();
    }

    private void showBalance(BigDecimal bal)
    {
        view.displayBalance(bal);
    }

    private String getMenuSelection() {

        return view.getMenuSelection();
    }

    private void quit()
    {
        view.displayQuitMessage();
    }

    private BigDecimal addFunds(BigDecimal balance) {
        BigDecimal funds = view.displayAndGetFunds();
        return funds.add(balance);
    }

    private Item getItemSelected(int index) throws VendingMachinePersistenceException {
        return service.listAllItems().get(index);
    }

    private BigDecimal vendItem(Item item, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
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
