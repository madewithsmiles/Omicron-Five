package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.entity.TransactionLogEntity;
import edu.ttu.cs.se.systeminterface.MGRPrinterInterface;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Application logic for generating reports for supermarket
 *
 * @author Justin Aguilar
 * created on 11/17/2018
 */
public class ReportLogic
{

    /**
     * Writes the transaction log to a file
     *
     * @param fileName where to output the log file
     */
    public static void buildTransLog(String fileName) {

        String source = "Total number of transactions: "
                + TransactionLogEntity.getTransactions()
                + ".\nTotal Sales: " + TransactionLogEntity.getRevenue()
                + ".\n End of Report";

        byte buf[] = source.getBytes();
        FileOutputStream f0;

        try {
            f0 = new FileOutputStream(fileName);
            f0.write(buf);
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        }
        MGRPrinterInterface.print(source);
    }


    /**
     * Builds and writes inventory report to file
     *
     * @param fileName where to output the log file
     */
    public static void buildInvReport(String fileName)
    {
        StringBuilder source = new StringBuilder();
        ArrayList<String> items = InventoryEntity.getKeys();

        for (String name : items) {
            Integer[] arr = InventoryEntity.getInfo(name).getValue();
            if (!arr[0].equals(arr[1])) {
                source.append(name).append("\n");
            }
        }

        byte buf[] = source.toString().getBytes();
        FileOutputStream f0;

        try {
            f0 = new FileOutputStream(fileName);
            f0.write(buf);
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        }

    }

}