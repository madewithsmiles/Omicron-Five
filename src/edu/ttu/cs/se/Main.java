package edu.ttu.cs.se;

import edu.ttu.cs.se.entity.InventoryEntity;

public class Main {
    private static InventoryEntity inventory;

    private static void initSession() {
        inventory = new InventoryEntity();
        //Simple Test
        //String test = "Soft Cremes";
        //ItemEntity test_result = inventory.getItem(test);
        //System.out.println("Name: " + test_result.getName());
        //System.out.println("Price: " + test_result.getPrice());
        //System.out.println("Description: " + test_result.getDesc());
        //System.out.println("Alcohol: " + test_result.getAlcohol());
        //System.out.println("Quantity: " + inventory.getQuantity(test));
    }

    public static void main(String[] args) {
	// write your code here
        initSession();
    }


}
