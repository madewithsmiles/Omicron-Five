package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.entity.TransactionLog;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class ReportLogic

    /* buildTransLog takes in a transaction log object and builds a text file named
    transactionfile.txt which contains the information in source.

     */

{

    /**
     * Writes the transaction log to a file
     *
     * @param fileName where to output the log file
     */
    public static void buildTransLog(String fileName) {

        String source = "Total number of transactions: " + TransactionLog.getTransactions() +
                ".\nTotal Sales: " + TransactionLog.getRevenue() + ".\n End of Report";

        byte buf[] = source.getBytes();
        FileOutputStream f0;

        try {
            f0 = new FileOutputStream(fileName);
            f0.write(buf);
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        }

    }


    /**
     * Builds and writes inventory report to file
     *
     * @param fileName where to output the log file
     */
    public static void buildInvReport(String fileName)
    {
        String source = "";
        ArrayList<String> items = InventoryEntity.getKeys();

        for (String name : items) {
            Integer[] arr = InventoryEntity.getInfo(name).getValue();
            if (arr[0] != arr[1]) {
                source += name + "\n";
            }
        }

        byte buf[] = source.getBytes();
        FileOutputStream f0;

        try {
            f0 = new FileOutputStream(fileName);
            f0.write(buf);
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        }

    }

}