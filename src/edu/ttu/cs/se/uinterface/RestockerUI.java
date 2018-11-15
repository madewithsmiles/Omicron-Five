package edu.ttu.cs.se.uinterface;

import edu.ttu.cs.se.applogic.RestockInventoryLogic;

import java.util.Scanner;

public class RestockerUI {

    public static void scanItems()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item:");
        String itemName = scanner.nextLine().trim();
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();
        RestockInventoryLogic.processRestock(itemName, quantity);
    }





}
