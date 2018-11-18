/**
 * @author Justin Aguilar
 * created 11/17/2018
 */
package edu.ttu.cs.se.uinterface;

import edu.ttu.cs.se.applogic.CheckoutLogic;
import edu.ttu.cs.se.applogic.IOHelper;
import edu.ttu.cs.se.systeminterface.ReceiptPrinterInterface;


public class CheckoutUI {
    public enum DisplayCode {
        WELCOME,
        NORMAL,
        TOTAL,
        RECEIPT,
    }

    public static void display(DisplayCode code, String info) {
        String output = "";
        switch (code) {
            case NORMAL:
                output = "Your order: \n\n" +
                        CheckoutLogic.getOrderContent() + "\n\n" + CheckoutLogic.getSubTotal() + "\n\n" +
                        "1. Scan Item\n2. Total & Payment\n3. Cancel Order\n";
                break;
            case WELCOME:
                output = "Welcome, Please press start to begin your order.";
                break;
            case TOTAL:
                output = "Your total is " + info + "\n\n";
            case RECEIPT:
                output = info;
                break;
        }
        System.out.println(output);
    }

    public static void scanItem()
    {
        String item = IOHelper.getInputString("Enter your item");
        //System.out.println("Item entered: " + item);
        boolean alcohol = CheckoutLogic.addItem(item);
        if (!alcohol)
        {
            String auth = IOHelper.getInputString("Manager Authorization Required");
            while (!CheckoutLogic.verifyEmployeeID(auth))
            {
                System.out.println("Error in code...");
                auth = IOHelper.getInputString("Manager Authorization Required");
            }
            alcohol = CheckoutLogic.addItem(item);
        }
    }

    public static void startCheckout() {
        CheckoutLogic.createOrder();
    }

    public static void total()
    {
        boolean paymentStatus = false;
        while (!paymentStatus) {
            display(DisplayCode.TOTAL, String.valueOf(CheckoutLogic.getOrderTotal()));
            int pmt = IOHelper.getInputInt("Enter your payment method: \n1. Cash\n2. Credit\n3. Debit", false);
            int cardNumber;
            switch (pmt) {
                case 1:
                    double cash = IOHelper.getInputDouble("Enter amount of cash", false);
                    paymentStatus = CheckoutLogic.payByCash(cash);
                    break;
                case 2:
                    cardNumber = IOHelper.getCustomInt("Enter your card number", 8);
                    paymentStatus = CheckoutLogic.payByCredit(cardNumber);
                    break;
                case 3:
                    cardNumber = IOHelper.getCustomInt("Enter your card number", 8);
                    int pin = IOHelper.getCustomInt("Enter PIN", 4);
                    paymentStatus = CheckoutLogic.payByDebit(cardNumber, pin);
                    break;
            }
        }
        String receipt = CheckoutLogic.processOrder();
        display(DisplayCode.RECEIPT, receipt);
        ReceiptPrinterInterface.printReceipt(receipt);
    }
}