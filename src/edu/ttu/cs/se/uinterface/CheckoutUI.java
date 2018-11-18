package edu.ttu.cs.se.uinterface;

import edu.ttu.cs.se.applogic.CheckoutLogic;
import edu.ttu.cs.se.applogic.IOHelper;
import edu.ttu.cs.se.systeminterface.ReceiptPrinterInterface;
import java.text.DecimalFormat;

/**
 * The User Interface class implementic displaying and inputting logic
 * for the checkout use case.
 *
 * @author Justin Aguilar
 * created on 11/17/2018
 */
public class CheckoutUI {
    /**
     * Enum class containing possible values passed to display
     * function.
     */
    public enum DisplayCode {
        WELCOME,
        NORMAL,
        TOTAL,
        RECEIPT,
    }

    /**
     * Displays a message to the client accordingly to an enum
     * value from DisplayCode.
     *
     * @param code the value from the DisplayCode enum.
     * @param info an optional parameter which is needed in some cases.
     */
    public static void display(DisplayCode code, String info) {
        String output = "";
        switch (code) {
            case NORMAL:
                output = "Your order: \n\n"
                        + CheckoutLogic.getOrderContent() + "\n\n"
                        + new DecimalFormat("###.##").format(CheckoutLogic.getSubTotal())
                        + "\n\n1. Scan Item\n2. Total & Payment\n3. Cancel Order\n";
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

    /**
     * Implements item scan display and input processes.
     */
    public static void scanItem()
    {
        String item = IOHelper.getInputString("Enter your item");
        boolean alcohol = CheckoutLogic.addItem(item);
        if (!alcohol)
        {
            String auth = IOHelper.getInputString("Employee Authorization Required");
            while (!CheckoutLogic.verifyEmployeeID(auth))
            {
                System.out.println("Error in code...");
                auth = IOHelper.getInputString("Employee Authorization Required");
            }
            CheckoutLogic.addItem(item);
        }
    }

    /**
     * Initiates the checkout process by calling a special CheckoutLogic class method.
     */
    public static void startCheckout() {
        CheckoutLogic.createOrder();
    }

    /**
     * Acts as a "total" button used by the customer to end the checkout and pay for
     * the items.
     */
    public static void total()
    {
        boolean paymentStatus = false;
        while (!paymentStatus) {
            display(DisplayCode.TOTAL, String.valueOf(CheckoutLogic.getOrderTotal()));
            int pmt = IOHelper.getInputInt("Enter your payment method: \n1. Cash\n2. Credit" +
                                            "\n3. Debit", false);
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