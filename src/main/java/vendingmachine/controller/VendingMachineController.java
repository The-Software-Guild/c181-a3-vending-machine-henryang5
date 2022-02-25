package vendingmachine.controller;

import vendingmachine.dao.VendingMachineDao;
import vendingmachine.service.VendingMachineService;
import vendingmachine.ui.VendingMachineView;

public class VendingMachineController {
    private VendingMachineView view;
    private VendingMachineService service;

    public VendingMachineController(VendingMachineView view, VendingMachineService service) {
        this.view = view;
        this.service = service;
    }

    public void run()
    {

    }
}
