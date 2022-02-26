package vendingmachine.service;

public class VendingMachineNoItemInventoryException extends Exception{
    public VendingMachineNoItemInventoryException(String message) {
        super(message);
    }

    public VendingMachineNoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
