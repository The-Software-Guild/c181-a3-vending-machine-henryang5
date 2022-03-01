package vendingmachine.service;

import vendingmachine.dao.VendingMachineAudioDao;
import vendingmachine.dao.VendingMachinePersistenceException;

public class VendingMachineAuditDaoStubImpl implements VendingMachineAudioDao {
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        // testing. does nothing
    }
}
