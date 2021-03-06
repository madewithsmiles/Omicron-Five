package edu.ttu.cs.se.entity;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static edu.ttu.cs.se.entity.ItemEntity.COL_SEP;

/**
 * The InventoryEntity class is the entity which stores all
 * items
 */
public class InventoryEntity {
    // To regenerate data, eval following link after cd in "data":
    // curl "https://api.mockaroo.com/api/e7ea3110?count=1000&key=164ae7f0" > "initialItems.csv"
    // curl "https://api.mockaroo.com/api/9f774950?count=1000&key=164ae7f0" > "initialItems1.csv"
    // FINAL DATA: curl "https://api.mockaroo.com/api/0a52da10?count=153&key=164ae7f0" > "initialItemsFinal.csv"
    // It will replace the current value with initialItems
    static final String DATABASE_NAME = "initialItemsFinal.csv";
    static private HashMap<String, Pair<ItemEntity, Integer[]>> items = null;

    public enum ItemStatus {
        GOOD,
        ISALCOHOL,
        INEXISTENT
    }

    /**
     * The class constructor which calls another function
     * to fill
     */
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
                if(!info[0].trim().equals("Name")) {
                    // Parse input
                    String name = info[0].trim().toLowerCase();
                    Double price = Double.parseDouble(info[1].trim());
                    Boolean alcohol = Double.parseDouble(info[2].trim()) == 1;
                    String desc = info[3].trim();
                    Double disc = Double.parseDouble(info[4].trim());
                    Integer qty = Integer.parseInt(info[5].trim());
                    Integer dQty = Integer.parseInt(info[6].trim());
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
    public static ItemEntity getItem(String name) {
        return items.get(name.toLowerCase()).getKey();
    }

    /**
     * Retrieves an array list of strings containing the keys to an InventoryEntity
     *
     * @return ArrayList the list of all keys to an InventoryEntity
     */

    public static ArrayList<String> getKeys(){
        return new ArrayList<>(items.keySet());
    }

    /**
     * Checks for the existence of an item in an InventoryEntity
     *
     * @param name the name of the item to check
     * @return      the result of whether the item exists in the InventoryEntity
     */

    public static Boolean exists(String name) {
        return items.containsKey(name);
    }

    /**
     * Retrieves ItemEntity, quantity and desired quantity given item name
     *
     * @param prodName item name to be retrieved
     * @return ItemEntity, quantity and desired quantity for given item name
     */

    public static Pair<ItemEntity, Integer[]> getInfo(String prodName) {
        return items.get(prodName.toLowerCase());
    }

    /**
     * Replace/Add a new ItemEntity and its quantities in the InventoryEntity at the specified name
     *
     * @param item the new ItemEntity
     * @param quantitiy the new quantity
     * @param desiredQuantity the new desired quantity
     */
    public static void setInfo(ItemEntity item, int quantitiy, int desiredQuantity) {
        items.put(item.getName(), new Pair<>(item, new Integer[]{quantitiy, desiredQuantity}));
    }

    /**
     * Gets a custom string representation of the inventory
     *
     * @param includeName whether to include the name in the output.
     * @param includeDescription whether to include the description in the output.
     * @param includePrice whether to include the price in the output.
     * @param includeDiscount whether to include the discount in the output.
     * @param includeAlcohol whether to include whether the item has alcohol.
     * @param includeQuantity whether to include the quantity in the output.
     * @param includeDQuantity whether to include the DQuantiy in the output.
     * @param specifiedSize the number of items to be returned.
     * @return
     */
    public static String getString(Boolean includeName, Boolean includeDescription, Boolean includePrice,
                            Boolean includeDiscount, Boolean includeAlcohol, Boolean includeQuantity,
                            Boolean includeDQuantity, Integer specifiedSize) {

        StringBuilder sb = new StringBuilder();
        String[] cols = new String[]{"Item #","Name","Description","Price(in $)", "Alcohol","Discount", "Qty","DQty"};
        final Boolean[] includeElem = new Boolean[]{includeName,includeDescription,includePrice, includeDiscount,
                                              includeAlcohol,includeQuantity,includeDQuantity};
        //Build header
        sb.append(String.format("%-6s",cols[0]));
        sb.append(COL_SEP);
        Integer row_length = 4 + 1;
        Integer elems = 0;
        for(int i=0; i < includeElem.length; i++) {
                if (includeElem[i]) {
                elems += 1;
                sb.append(String.format("%-25s",cols[i+1]));
                sb.append(COL_SEP);
                }
        }

        sb.append("\n");

        row_length = row_length + 25 * elems + elems + 1;
        for (int i = 0; i < row_length; i++) {
            sb.append("-");
        }
        sb.append("\n");

        if(specifiedSize == -1) {
            specifiedSize = items.size();
        }

        if(specifiedSize > items.size()) {
            specifiedSize = items.size();
            System.out.println("ERROR. SPECIFIED SIZE > ITEMS.SIZE().\nSPECIFIED SIZE set to ITEMS.SIZE()");
        }

        Integer counter = 1;
        for (Map.Entry<String, Pair<ItemEntity, Integer[]>> entry: items.entrySet()) {
            if(counter <= specifiedSize) {
                sb.append(String.format("%-6s",String.valueOf(counter)));
                sb.append(COL_SEP);
                sb.append(entry.getValue().getKey()
                        .getString(includeElem[0],includeElem[1],includeElem[2],includeElem[3],includeElem[4]));
                if (includeElem[5]) {
                    sb.append(String.format("%-25s",String.valueOf(entry.getValue().getValue()[0])));
                    sb.append(COL_SEP);
                }
                if (includeElem[6]) {
                    sb.append(String.format("%-25s",String.valueOf(entry.getValue().getValue()[1])));
                    sb.append(COL_SEP);
                }
                sb.append("\n");
                counter += 1;
            }
            else {
                break;
            }
        }

        return sb.toString();
    }


}
