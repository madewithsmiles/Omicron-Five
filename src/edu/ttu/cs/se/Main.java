package edu.ttu.cs.se;

import edu.ttu.cs.se.applogic.IOHelper;
import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.uinterface.CheckoutUI;

public class Main {

    private static InventoryEntity inventory;
    private static CheckoutUI checkout;

    public static void main(String[] args)
    {
        initSession();
        run();
    }

    private static void run() {
        System.out.println("Self checkout main menu");
        System.out.println("1) Self checkout");
        System.out.println("2) Manager");
        System.out.println("3) Restocker");
        int choice = Integer.parseInt(IOHelper.getInputString("Choice"));

        switch (choice) {
            case 1:
                checkoutMenu();
                break;
            case 2:
                managerMenu();
                break;
            case 3:
                restockerMenu();
                break;
        }

    }

    private static void initSession()
    {
        inventory = new InventoryEntity();
        checkout = new CheckoutUI();
    }

    private static void restockerMenu() {

    }

    private static void checkoutMenu() {
        checkout.display(CheckoutUI.DisplayCode.WELCOME);
        checkout.startCheckout();
        while (true) {
            checkout.display(CheckoutUI.DisplayCode.NORMAL);
            int input = IOHelper.getInputInt("Choice");
            boolean done = false;
            switch (input) {
                case 1:
                    checkout.scanItem();
                    break;
                case 2:
                    checkout.total();
                    done = true;
                    break;
            }
            if (done) {
                break;
            }
        }
        checkoutMenu();
    }

    private static void managerMenu() {

    }



}
