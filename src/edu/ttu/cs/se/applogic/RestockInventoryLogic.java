package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.InventoryEntity;

public class RestockInventoryLogic
{

    /**
     * If the item name exists in inventory, add
     * a certain amount to it.
     *
     * @param name name of the item to add
     * @param quantity amount of the item to add
     */
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
