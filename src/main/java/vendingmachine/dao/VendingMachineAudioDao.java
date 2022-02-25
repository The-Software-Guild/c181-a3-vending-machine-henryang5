package vendingmachine.dao;

public interface VendingMachineAudioDao {
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
