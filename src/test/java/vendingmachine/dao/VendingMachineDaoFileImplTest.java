package vendingmachine.dao;

import org.junit.jupiter.api.*;
import vendingmachine.dto.Item;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineDaoFileImplTest {

    VendingMachineDao testDao;

    @BeforeAll
    static void beforeAll() {}

    @AfterAll
    static void afterAll() {}

    @BeforeEach
    void setUp() throws Exception {
        String testFile = "test.txt";
        testDao = new VendingMachineDaoFileImpl(testFile);
    }

    @AfterEach
    void tearDown() {}

    @org.junit.jupiter.api.Test
    void getItem() throws Exception {
        Item item1 = testDao.getItem("Pepsi");
        assertNotNull(item1, "Item must not be null");
        assertEquals(10, item1.getNumInventoryItems(), "Inventory count should be 10");
        assertEquals(1.99, item1.getCost().doubleValue(), "Cost should be 1.99");

        Item item2 = testDao.getItem("Gum");
        assertNotNull(item2, "Item must not be null");
        assertEquals(0, item2.getNumInventoryItems(), "Inventory count should be 0");
        assertEquals(0.49, item2.getCost().doubleValue(), "Cost should be 0.49");

    }

    @org.junit.jupiter.api.Test
    void listAllItems() throws Exception {
        List<Item> testList = testDao.listAllItems();

        assertNotNull(testList, "Item list must not be null");
        assertEquals(6, testList.size(), "List should have 6 items");
        assertTrue(testList.contains(testDao.getItem("Snickers Bar")), "List should have Snickers Bar");
        assertTrue(testList.contains(testDao.getItem("Potato Chip")), "List should have Pepsi");
        assertTrue(testList.contains(testDao.getItem("Pepsi")), "List should have snickers bar");
        assertTrue(testList.contains(testDao.getItem("Cheetos")), "List should have Cheetos");
        assertTrue(testList.contains(testDao.getItem("Mountain Dew")), "List should have Mountain Dew");
        assertTrue(testList.contains(testDao.getItem("Gum")), "List should have Gum");
        assertFalse(testList.contains(testDao.getItem("")), "List should not have empty string");
    }

    @org.junit.jupiter.api.Test
    void changeInventoryCount() throws Exception {
        Item item1 = testDao.getItem("Potato Chip");
        assertNotNull(item1, "Item must not be null");
        testDao.changeInventoryCount(item1, 100);
        assertEquals(100, item1.getNumInventoryItems(), "Inventory count should be 100");

        Item item2 = testDao.getItem("Cheetos");
        assertNotNull(item2, "Item must not be null");
        testDao.changeInventoryCount(item2, 0);
        assertEquals(0, item2.getNumInventoryItems(), "Inventory count should be 0");
    }
}