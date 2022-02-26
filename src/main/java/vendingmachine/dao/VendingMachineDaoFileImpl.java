package vendingmachine.dao;

import vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao{
    Map<String, Item> itemMap;
    private static final String ITEM_FILE = System.getProperty("user.dir") + "/items.txt";
    private static final String DELIMITER = ",";

    public VendingMachineDaoFileImpl() throws VendingMachinePersistenceException {
        itemMap = new HashMap<>();
        readFile();
    }

    private void readFile() throws VendingMachinePersistenceException {
        try {
            Scanner sc = new Scanner(new FileReader(ITEM_FILE));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Item curItem = unmarshallItems(line);
                itemMap.put(curItem.getName(), curItem);
            }
        }
        catch(FileNotFoundException e)
        {
            throw new VendingMachinePersistenceException("File not found", e);
        }
    }

    private Item unmarshallItems(String line)
    {
        String[] data = line.split(DELIMITER);
        String name = data[0];
        BigDecimal cost = new BigDecimal(data[1]);
        int numInventory = Integer.parseInt(data[2]);
        Item newItem = new Item(name, cost, numInventory);

        return newItem;
    }

    private String marshallItems(Item item) throws VendingMachinePersistenceException
    {
        return item.getName() + DELIMITER + item.getCost() + DELIMITER + item.getNumInventoryItems() + "\n";
    }

    public void writeFile() throws VendingMachinePersistenceException
    {
        try {
            FileWriter myWriter = new FileWriter(ITEM_FILE, false); // append set to false
            List<Item> itemList = this.listAllItems();
            for(Item curItem : itemList) {
                myWriter.write(marshallItems(curItem));
                myWriter.flush();
            }
            myWriter.close();
        }
        catch(IOException e)
        {
            throw new VendingMachinePersistenceException("Could not save Dvd data", e);
        }
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        readFile();
        return itemMap.get(name);
    }

    @Override
    public List<Item> listAllItems() throws VendingMachinePersistenceException {
        readFile();
        return new ArrayList<>(itemMap.values());
    }

    @Override
    public Item addItem(Item item) throws VendingMachinePersistenceException {
        Item addedItem = itemMap.put(item.getName(), item);
        writeFile();
        return addedItem;
    }

    @Override
    public Item removeItem(Item item) throws VendingMachinePersistenceException {
        readFile();
        Item removedItem = itemMap.remove(item.getName());
        writeFile();
        return removedItem;
    }

    @Override
    public void changeInventoryCount(Item item, int newCount) throws VendingMachinePersistenceException {
        item.setNumInventoryItems(newCount);
        writeFile();
    }
}
