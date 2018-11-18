package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.uinterface.RestockerUI;

/**
 * Application logic for restocking store inventory
 *
 * @author Ryan Kelley
 * created on 11/17/2018
 */
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
        if (InventoryEntity.exists(name))
        {
            InventoryEntity.addItems(name, quantity);
        }
        else
        {
            if(IOHelper.getInputString("The item " + name  + " does not exist, do you want to add it (yes/no)?")
                    .equalsIgnoreCase("yes")) {
                RestockerUI.newItem(name, quantity);
            }
        }
    }

}
