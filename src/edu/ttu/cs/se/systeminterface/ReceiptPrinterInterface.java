package edu.ttu.cs.se.systeminterface;

/**
 * Receipt printer interface for receipt printing device at self checkout kiosk
 *
 * @author Justin Aguilar
 * created on 11/17/2018
 */
public class ReceiptPrinterInterface {

    /**
     * Prints receipt to receipt printer
     *
     * @param receipt message to print on receipt
     */
    public static void printReceipt(String receipt) {
        System.out.println("ReceiptPrinterInterface: Receipt received and sent to receipt printer.");
    };
}