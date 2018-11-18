package edu.ttu.cs.se;

import edu.ttu.cs.se.applogic.CheckoutLogic;
import edu.ttu.cs.se.applogic.IOHelper;
import edu.ttu.cs.se.control.ReportTimer;
import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.uinterface.CheckoutUI;
import edu.ttu.cs.se.uinterface.ManagerUI;
import edu.ttu.cs.se.uinterface.RestockerUI;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;


public class Main {

    private static InventoryEntity inventory;

    public static void main(String[] args)
    {
        initSession();
        run();
    }

    private static void run() {
        IOHelper.printHeader();
        System.out.println("Self checkout main menu");
        System.out.println("1) Self checkout");
        System.out.println("2) Manager");
        System.out.println("3) Restocker");
        IOHelper.printFooter();
        int choice = IOHelper.getInputInt("Choice", false);

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
        CheckoutLogic.initializeLogic();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime midnight = now.toLocalDate().atStartOfDay();
        Date d1 = Date.from(midnight.atZone(ZoneId.systemDefault()).toInstant());
        long oneDay = 86400000;
        ReportTimer runReports = new ReportTimer();
        Timer timeToRun = new Timer(true);
        timeToRun.scheduleAtFixedRate(runReports,d1,oneDay);
    }

    private static void restockerMenu() {
        boolean choice = true;
        while (choice) {
            RestockerUI.scanItems();
            choice = IOHelper.getInputString("Continue? (yes/no)").equalsIgnoreCase("yes");

        }
        run();
    }


    private static void checkoutMenu() {
        CheckoutUI.display(CheckoutUI.DisplayCode.WELCOME, "");
        CheckoutUI.startCheckout();
        while (true) {
            CheckoutUI.display(CheckoutUI.DisplayCode.NORMAL, "");
            int input = IOHelper.getInputInt("Choice", false);
            boolean done = false;
            switch (input) {
                case 1:
                    CheckoutUI.scanItem();
                    break;
                case 2:
                    CheckoutUI.total();
                    done = true;
                    break;
                case 3:
                    CheckoutLogic.flushOrder();
                    done = true;
                    break;
            }
            if (done) {
                break;
            }
        }
        run();
    }

    private static void managerMenu() {
        System.out.println("Manager Menu");
        System.out.println("1) View");
        System.out.println("2) Update");
        System.out.println("3) Exit");

        int choice = IOHelper.getInputInt("Enter choice", false);
        boolean done = false;

        switch (choice) {
            case 1:
                ManagerUI.viewInventory(10);
                break;
            case 2:
                ManagerUI.updateInventory();
                break;
            default:
                done = true;
                run();
                break;
        }

        if (!done) {
            managerMenu();
        }

    }



}
