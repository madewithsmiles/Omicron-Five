package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.entity.ItemEntity;
import javafx.util.Pair;

public class ManageProductLogic {

    /**
     * Processes item choice and updates inventory record respectively
     *
     * @param itemChoice item name to update
     * @param fieldChoice item property to update
     */
    public static void processChoice(String itemChoice, String fieldChoice) {

        Pair<ItemEntity, Integer[]> choice_value = InventoryEntity.getInfo(itemChoice); //gets the item entity and array
        ItemEntity tempItem = choice_value.getKey(); //tempItem will be calculated on
        Integer[] change_array = choice_value.getValue(); //qty and dqty array to pass

        switch (fieldChoice) {
            case "price":
                Double newPrice = IOHelper.getInputDouble("Enter new price", false);
                tempItem.setPrice(newPrice);
                System.out.println("New Price: " + newPrice);
                break;
            case "description":
                String newDescription = IOHelper.getInputString("Enter new description");
                tempItem.setDesc(newDescription);
                System.out.println("New Description: " + newDescription);
                break;
            case "discount":
                Double newDiscount = IOHelper.getInputDouble("Enter new discount", false);
                tempItem.setDiscount(newDiscount);
                System.out.println("New Discount: " + newDiscount);
                break;

        }
        InventoryEntity.setInfo(tempItem, change_array[0], change_array[1]);
    }
}