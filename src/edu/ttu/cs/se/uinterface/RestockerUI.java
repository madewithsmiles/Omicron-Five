package edu.ttu.cs.se.uinterface;

import edu.ttu.cs.se.applogic.IOHelper;
import edu.ttu.cs.se.applogic.RestockInventoryLogic;
import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.entity.ItemEntity;

import java.util.Scanner;

public class RestockerUI {


    /**
     *  Prompts for item name and quantity then restocks that item into inventory
     */
    public static void scanItems()
    {
        // TODO: Display all items to restocker
        // InventoryEntity.displayItems();
        String itemName = IOHelper.getInputString("Enter item name");
        int quantity = IOHelper.getInputInt("Enter quantity");
        RestockInventoryLogic.processRestock(itemName, quantity);
    }

    public static void newItem()
    {
        String name = IOHelper.getInputString("Enter new item name");
        double price = IOHelper.getInputDouble("Enter price");
        String desc = IOHelper.getInputString("Enter description");
        double disc = IOHelper.getInputDouble("Enter discount");
        boolean alc = Boolean.valueOf(IOHelper.getInputString("Enter alcohol (true/false)"));
        int quantity = IOHelper.getInputInt("Enter quantity");
        int invLevel = IOHelper.getInputInt("Enter inventory level");
        InventoryEntity.addNewItem(new ItemEntity(name, price, alc, desc, disc), quantity, invLevel);
    }





}
