package edu.ttu.cs.se.uinterface;

import edu.ttu.cs.se.applogic.IOHelper;
import edu.ttu.cs.se.applogic.RestockInventoryLogic;
import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.entity.ItemEntity;

/**
 * Encapsulates all restocker user interface functions
 *
 * @author Ryan Kelley
 * created on 11/17/2018
 */
public class RestockerUI {


    /**
     *  Prompts for item name and quantity then restocks that item into inventory
     */
    public static void scanItems() {
        System.out.println(InventoryEntity.getString(true, false, false,
                false, false, true,
                true, -1));
        String itemName = IOHelper.getInputString("Enter item name");
        int quantity = IOHelper.getInputInt("Enter quantity", false);
        RestockInventoryLogic.processRestock(itemName, quantity);
    }

    /**
     * Prompts restocker for new item details then adds that item to the inventory
     *
     * @param name name of item to add to inventory
     * @param quantity amount of item to add to inventory
     */
    public static void newItem(String name, int quantity) {
        double price = IOHelper.getInputDouble("Price", false);
        String desc = IOHelper.getInputString("Description");
        double disc = IOHelper.getInputDouble("Discount", false);
        boolean alc = IOHelper.getInputString("Alcoholic (yes/no)?").equalsIgnoreCase("yes");
        int invLevel = IOHelper.getInputInt("Desired quantity", false);
        InventoryEntity.addNewItem(new ItemEntity(name, price, alc, desc, disc), quantity, invLevel);
    }

}
