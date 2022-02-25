package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.dao.VendingMachineAudioDao;
import vendingmachine.dao.VendingMachineAudioDaoFileImpl;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoFileImpl;
import vendingmachine.service.VendingMachineService;
import vendingmachine.service.VendingMachineServiceImpl;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImpl;
import vendingmachine.ui.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAudioDao myAuditDao = new VendingMachineAudioDaoFileImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDao, myAuditDao);
        VendingMachineController controller =
                new VendingMachineController(myView, myService);
        controller.run();
    }
}
