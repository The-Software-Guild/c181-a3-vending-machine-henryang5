package vendingmachine.service;

import org.junit.jupiter.api.*;
import vendingmachine.dao.VendingMachineAudioDao;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachinePersistenceException;
import vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class VendingMachineServiceImplTest {
    private VendingMachineService service;

    public VendingMachineServiceImplTest()
    {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAudioDao auditDao = new VendingMachineAuditDaoStubImpl();
        service = new VendingMachineServiceImpl(dao, auditDao);
    }

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @BeforeAll
    static void beforeAll() {}

    @AfterAll
    static void afterAll() {}

    @Test
    public void testInsufficientFunds() throws Exception {
        Item testItem = new Item("Food",  new BigDecimal(5.00), 2);
        try {
            service.vendItem(new BigDecimal(0.01), testItem);
            fail("Expected VendingMachineInsufficientFundsException not thrown");
        }
        catch(VendingMachinePersistenceException | VendingMachineNoItemInventoryException e)
        {
            fail("Incorrect exception thrown");
        }
        catch(VendingMachineInsufficientFundsException e)
        {
            return;
        }

    }
    @Test
    public void testNoItemInventory() throws Exception {
        Item testItem = new Item("Drinks",  new BigDecimal(2.99), 0);
        try {
            service.vendItem(new BigDecimal(5.00), testItem);
            fail("Expected VendingMachineNoItemInventoryException not thrown");
        }
        catch(VendingMachinePersistenceException | VendingMachineInsufficientFundsException e)
        {
            fail("Incorrect exception thrown");
        }
        catch(VendingMachineNoItemInventoryException e)
        {
            return;
        }
    }

    @Test
    public void testListAllItems() throws Exception {
        Item testItem = new Item("Chips",  new BigDecimal(2.99), 2);
        List<Item> testList = new ArrayList<>();
        testList.add(testItem);
        assertEquals(1, service.listAllItems().size(), "Should have one item");
        assertEquals(service.listAllItems(), testList, "Test list should equal item list");
    }

    @Test
    public void testGetItem() throws Exception {
        Item testItem = new Item("Chips",  new BigDecimal(2.99), 2);
        assertNotNull(testItem, "Item should not be null");
        assertEquals(service.getItem("Chips"), testItem, "Test item should equal getItem");
    }

    @Test
    public void testChangeInventoryCount() throws Exception {
        Item testItem = new Item("Chips",  new BigDecimal(2.99), 2);
        service.changeInventoryCount(testItem, 100);
        assertNotNull(testItem, "Item should not be null");
        assertEquals(100, testItem.getNumInventoryItems(), "Inventory item should be 100");
    }
    @Test
    public void testVendItem() throws Exception {
        Item testItem = new Item("Monster",  new BigDecimal(7.99), 9);
        BigDecimal change = service.vendItem(new BigDecimal(50.5), testItem);
        assertNotNull(testItem, "Item should not be null");
        assertEquals(42.51, change.doubleValue(), "Change return should be 42.51");
    }

}