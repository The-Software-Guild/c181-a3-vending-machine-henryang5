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
        io.print("0. Quit");
    }

    public void printAllItems(List<Item> itemList)
    {
        for(int i = 0; i < itemList.size(); i++)
        {
            io.print(i + 1 + ": " + itemList.get(i));
        }
    }

    public int getMenuSelection()
    {
        return io.readInt("Please select an item.", 0, 100);
    }

    public void displayQuitMessage()
    {
        io.print("Goodbye" + "\n");
    }

    public BigDecimal displayAndGetFunds()
    {
        io.print("====ADD FUNDS====");
        return io.readBigDecimal("Enter funds to add: ", new BigDecimal(0), new BigDecimal(100));
    }

    public void displayErrorMessage(String message)
    {
        io.print(message);
    }

    public void displayBalance(BigDecimal bal)
    {
        NumberFormat usdCostFormat = NumberFormat.getCurrencyInstance(Locale.US);
        usdCostFormat.setMinimumFractionDigits( 2 );
        usdCostFormat.setMaximumFractionDigits( 2 );
        //bal.setScale(2, RoundingMode.HALF_UP);
        io.print("Current balance: " + usdCostFormat.format(bal.doubleValue()));
    }

    public void displayChange(Map<Coins, Integer> changeMap)
    {
        io.print("Change: " + changeMap.get(Coins.QUARTERS) + " quarters, " +
                    changeMap.get(Coins.DIME) + " dimes, " +
                    changeMap.get(Coins.NICKLE) + " nickles, " +
                    changeMap.get(Coins.PENNY) + " pennies");
    }


}
