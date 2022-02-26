package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.dao.*;
import vendingmachine.service.VendingMachineService;
import vendingmachine.service.VendingMachineServiceImpl;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImpl;
import vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAudioDao myAuditDao = new VendingMachineAudioDaoFileImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run();
    }
}
