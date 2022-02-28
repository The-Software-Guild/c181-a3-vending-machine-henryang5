package vendingmachine.dto;

import vendingmachine.service.VendingMachineInsufficientFundsException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

public class Change {
    private Map<Coins, Integer> coinChangeMap;

    public Change()
    {
        coinChangeMap = new HashMap<>();
    }

    public Map<Coins, Integer> getChange(BigDecimal funds, BigDecimal price) throws VendingMachineInsufficientFundsException {
        MathContext mc = new MathContext(4, RoundingMode.HALF_UP);

        BigDecimal changeAmount = funds.subtract(price);

        if(changeAmount.doubleValue() < 0)
        {
             throw new VendingMachineInsufficientFundsException("Not enough funds");
        }
        BigDecimal[] divRemArr;

        divRemArr = changeAmount.divideAndRemainder(Coins.QUARTERS.getValue(), mc);
        BigDecimal quarters = divRemArr[0];
        coinChangeMap.put(Coins.QUARTERS, quarters.intValue());
        changeAmount = divRemArr[1];

        divRemArr = changeAmount.divideAndRemainder(Coins.DIME.getValue(), mc);
        BigDecimal dimes = divRemArr[0];
        coinChangeMap.put(Coins.DIME, dimes.intValue());
        changeAmount = divRemArr[1];

        divRemArr = changeAmount.divideAndRemainder(Coins.NICKLE.getValue(), mc);
        BigDecimal nickles = divRemArr[0];
        coinChangeMap.put(Coins.NICKLE, nickles.intValue());
        changeAmount = divRemArr[1];

        BigDecimal pennies = changeAmount.divide(Coins.PENNY.getValue(), mc);
        coinChangeMap.put(Coins.PENNY, pennies.intValue());

        return coinChangeMap;
    }
}
