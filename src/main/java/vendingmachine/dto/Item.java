package vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String name;
    private BigDecimal cost;
    private int numInventoryItems;

    public Item(String name, BigDecimal cost, int numInventoryItems) {
        this.name = name;
        this.cost = cost;
        this.numInventoryItems = numInventoryItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getNumInventoryItems() {
        return numInventoryItems;
    }

    public void setNumInventoryItems(int numInventoryItems) {
        this.numInventoryItems = numInventoryItems;
    }

    @Override
    public String toString() {
        return name + ": " + "$" + cost + ", Count: " + numInventoryItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item that = (Item) o;
        return numInventoryItems == that.numInventoryItems && Objects.equals(name, that.name) && Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, numInventoryItems);
    }
}
