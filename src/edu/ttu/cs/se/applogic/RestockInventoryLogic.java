package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.InventoryEntity;

public class RestockInventoryLogic
{
    // Checks to see if the item name is an item in inventory, and updates it if it is.
    public static void processRestock(String name, int quantity)
    {
        if (checkItem(name))
        {
            InventoryEntity.addItems(name, quantity);
        }
    }

    private static boolean checkItem(String name)
    {
        return InventoryEntity.getItem(name) != null;
    }
}
