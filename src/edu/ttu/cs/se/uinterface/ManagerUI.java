package edu.ttu.cs.se.uinterface;

import edu.ttu.cs.se.applogic.IOHelper;
import edu.ttu.cs.se.applogic.ManageProductLogic;
import edu.ttu.cs.se.entity.InventoryEntity;

import java.util.ArrayList;

public class ManagerUI {


    /**
     * Prompts user to pick an item and edit its value
     */
    public static void updateInventory() {
        ArrayList<String> names = InventoryEntity.getKeys();

        viewInventory(10);

        int itemChoice = IOHelper.getInputInt("Please choose the number corresponding to the item you want to edit", false);

        String fieldChoice = IOHelper.getInputString("Please type field name (price, discount, description)");

        ManageProductLogic.processChoice(names.get(itemChoice -1),fieldChoice);

    }

    public static void viewInventory(int numItems) {
        ArrayList<String> items = InventoryEntity.getKeys();
        for (int j = 0; j < numItems; j++) {
            System.out.println(String.format("%d. %s", j + 1, items.get(j)));
        }
    }
}