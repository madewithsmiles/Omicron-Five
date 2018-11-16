package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.OrderEntity;
import edu.ttu.cs.se.systeminterface.ReceiptPrinterInterface;

public class CheckoutLogic implements ReceiptPrinterInterface {
    final Double TAX_RATE = 0.4;
    private static OrderEntity currentOrder = null;

    public static void createOrder() {
        currentOrder = new OrderEntity();
    }

    public static String getOrderContent() {
        return currentOrder.toString();
    }

    @Override
    public void printReceipt() {
        //TODO: Update the printReceipt() function
        System.out.println("Receipt printed");
    }
}
