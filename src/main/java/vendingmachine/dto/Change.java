package vendingmachine.dto;

import vendingmachine.service.VendingMachineInsufficientFundsException;

import java.math.BigDecimal;
import java.util.*;

public class Change {
    private Map<Coins, Integer> coinChangeMap;

    public Change()
    {
        coinChangeMap = new HashMap<>();
    }

    public Map<Coins, Integer> getChange(BigDecimal funds, BigDecimal price)
    {
        BigDecimal changeAmount = funds.subtract(price);
        if(changeAmount.doubleValue() < 0)
        {
             //throw new VendingMachineInsufficientFundsException();
        }
        BigDecimal[] divRemArr;

        divRemArr = changeAmount.divideAndRemainder(Coins.QUARTERS.getValue());
        BigDecimal quarters = divRemArr[0];
        coinChangeMap.put(Coins.QUARTERS, quarters.intValue());
        changeAmount = divRemArr[1];

        divRemArr = changeAmount.divideAndRemainder(Coins.DIME.getValue());
        BigDecimal dimes = divRemArr[0];
        coinChangeMap.put(Coins.DIME, dimes.intValue());
        changeAmount = divRemArr[1];

        divRemArr = changeAmount.divideAndRemainder(Coins.NICKLE.getValue());
        BigDecimal nickles = divRemArr[0];
        coinChangeMap.put(Coins.NICKLE, dimes.intValue());
        changeAmount = divRemArr[1];


        divRemArr = changeAmount.divideAndRemainder(Coins.PENNY.getValue());
        BigDecimal pennies = divRemArr[0];
        coinChangeMap.put(Coins.PENNY, pennies.intValue());

        return coinChangeMap;
    }
}
