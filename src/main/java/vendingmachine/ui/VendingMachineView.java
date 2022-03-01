package vendingmachine.ui;

import vendingmachine.dto.Coins;
import vendingmachine.dto.Item;

import java.math.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VendingMachineView {
    private UserIO io;

    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }

    public void  printMenu() {
        io.print("======Main Menu========");
        io.print("q. Quit");
        io.print("a. Add Money");
        io.print("b. Buy Item");
    }

    public void printAllItems(List<Item> itemList)
    {
        io.print("=======Vending Machine Items==========");
        for(int i = 0; i < itemList.size(); i++)
        {
            io.print(i + 1 + ": " + itemList.get(i));
        }
    }

    public String getMenuSelection()
    {
        String operation =  io.readString("Please select an operation.");
        return operation;
    }

    public void displayQuitMessage()
    {
        io.print("Goodbye" + "\n");
    }

    public BigDecimal displayAndGetFunds()
    {
        io.print("====ADD FUNDS====");
        BigDecimal funds = io.readBigDecimal("Enter funds to add ($0 - $100): ", new BigDecimal(0), new BigDecimal(100));
        io.print("Funds added");
        io.readString("Please hit enter to continue.");
        return funds;
    }

    public void displayErrorMessage(String message)
    {
        io.print(message + '\n');
        io.readString("Please hit enter to continue.");
    }

    public void displayBalance(BigDecimal bal)
    {
        NumberFormat usdCostFormat = NumberFormat.getCurrencyInstance(Locale.US);
        usdCostFormat.setMinimumFractionDigits( 2 );
        usdCostFormat.setMaximumFractionDigits( 2 );
        io.print("Current balance: " + usdCostFormat.format(bal.doubleValue()));
    }

    public void displayChange(Map<Coins, Integer> changeMap)
    {
        io.print("Change: " + changeMap.get(Coins.QUARTERS) + " quarters, " +
                    changeMap.get(Coins.DIME) + " dimes, " +
                    changeMap.get(Coins.NICKLE) + " nickles, " +
                    changeMap.get(Coins.PENNY) + " pennies");
        io.readString("Please hit enter to continue.");
    }


    public void displayUnknownCommand() {
        io.print("Invalid input. Please input 0, 1 or 2" + "\n");
    }

    public int getItemSelection() {
        int operation =  io.readInt("Please select an item.");
        return operation;
    }

    public void printInvalidItem() {
        io.print("Invalid item entered. Please enter an item shown in machine.");
    }
}
