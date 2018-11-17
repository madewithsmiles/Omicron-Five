package edu.ttu.cs.se.entity;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * TODO: Write InventoryEntity comments
 * TODO: and add description comments before each function
 * TODO: Generate JavaDoc at the end
 */
public class InventoryEntity {
    // To regenerate data, eval following link after cd in "data":
    // curl "https://api.mockaroo.com/api/e7ea3110?count=1000&key=164ae7f0" > "initialItems.csv"
    // curl "https://api.mockaroo.com/api/9f774950?count=1000&key=164ae7f0" > "initialItems1.csv"
    // It will replace the current value with initialItems
    static final String DATABASE_NAME = "initialItems1.csv";
    static private HashMap<String, Pair<ItemEntity, Integer[]>> items = null;

    public InventoryEntity() {
        initialize();
    }

    private void initialize() {
        String line;
        String separator = ",";
        String cwd = System.getProperty("user.dir");
        String osFiSep= System.getProperty("file.separator");
        String file_loc = cwd + osFiSep + "src" + osFiSep + "edu" + osFiSep + "ttu"
        + osFiSep + "cs" + osFiSep + "se" + osFiSep + "data" + osFiSep + DATABASE_NAME;

        items = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file_loc))) {

            while ((line = br.readLine()) != null) {

                String[] info = line.split(separator);
                if(!info[0].equals("Name")) {
                    // Parse input
                    String name = info[0].toLowerCase();
                    Double price = Double.parseDouble(info[1]);
                    Boolean alcohol = Double.parseDouble(info[2]) == 1;
                    String desc = info[3].trim();
                    Double disc = Double.parseDouble(info[4]);
                    Integer qty = Integer.parseInt(info[5]);
                    Integer dQty = Integer.parseInt(info[6]);
                    // Populate the HashMap with parsed input
                    items.put(name,
                            new Pair<>(
                                    new ItemEntity(name,price,alcohol,desc,disc),
                                    new Integer[]{qty, dQty}
                            )
                    );
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the quantity of a specified item
     *
     * @param name the name of the item
     * @return     the quantity of the item with the specified name
     */
    public static Integer getQuantity(String name) {
        return items.get(name.toLowerCase()).getValue()[0];
    }

    /**
     * Updates the quantity of an item by a given quantity
     *
     * @param name the name of the item
     * @param qty the quantity we would like to add by
     */
    public static void addItems(String name, Integer qty) {
        Integer newQty = qty + items.get(name).getValue()[0];
        newQty = newQty < 0 ? 0 : newQty;
        Pair<ItemEntity, Integer[]> item = items.get(name.toLowerCase());
        item.getValue()[0] = newQty;

        items.put(name.toLowerCase(),item);
    }

    public static void addNewItem(ItemEntity item, Integer qty, Integer dQty) {
        items.put(item.getName().toLowerCase(), new Pair<>(item, new Integer[]{qty, dQty}));
    }

    /**
     * Decreases the value of a specified item by a given
     * quantity by making use of one of the other public
     * methods.
     *
     * @param name the name of the item
     * @param qty the quantity we would like to remove
     */
    public static void removeItems(String name, Integer qty) {
        addItems(name, -qty);
    }

    /**
     * Retrieves the ItemEntity instance for the specified item
     *
     * @param name the name of the item
     * @return     the ItemEntity instance with that name
     */
    public ItemEntity getItem(String name) {
        return items.get(name.toLowerCase()).getKey();
    }

//    public String getString(Boolean includeName, Boolean includePrice, Boolean includeAlcohol,
//                            Boolean include)


}
