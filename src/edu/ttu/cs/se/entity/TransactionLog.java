package edu.ttu.cs.se.entity;
import java.text.DecimalFormat;

public class TransactionLog {

    private static int transactions = 0;
    private static double revenue = 0.0;


    /**
     * Gets the revenue as currency string
     *
     * @return the transaction revenue formatted as a currency string
     */
    public static String getRevenue() {
        return new DecimalFormat("$#.00").format(revenue);
    }

    /**
     * Gets the total number of transactions
     *
     * @return the total number of transactions
     */
    public static int getTransactions() {
        return transactions;
    }

    /**
     * Adds a dollar amount to the current revenue
     *
     * @param amt the amount to add to the current revenute
     */
    public static void addRevenue(double amt) {
        revenue += amt;
    }

    /**
     * Increments transaction amount by one
     */
    public static void incTransactions() {
        transactions += 1;
    }

    /**
     * Resets transactions and revenue to zero
     */
    public static void reset() {
        transactions = 0;
        revenue = 0.0;
    }
}
