package edu.ttu.cs.se.systeminterface;

/**
 * An Interface to communicate with the Bank and authorize
 * transactions
 *
 * @author Justin Aguilar
 * created on 11/17/2018
 */
public class BankInterface
{
    /**
     * An integer acting as a custom authorization number
     */
    private static int authNumCounter = 1000;

    /**
     * Checks to see if a pin number is valid or not
     *
     * @param pin the pin number to check
     * @return whether the pin is valid or not
     */
    private static boolean validPin(int pin) {
        return pin % 2 == 0;
    }

    /**
     * Checks to see if a credit card is valid or not
     *
     * @param cardNumber credit card number to check
     * @return authorization number if valid, -1 otherwise
     */
    public static int authorizeCredit(int cardNumber)
    {
        // Card is valid if it is even
        if (cardNumber % 2 == 0) {
            return ++authNumCounter;
        }
        return -1;
    }

    /**
     * authorizeDebit takes in customer information, tests it and returns either a pass or fail code
     *
     * @param cardNumber debit card number to check
     * @param pin debit card pin number
     * @return authorization number if valid, -1 otherwise
     */
    public static int authorizeDebit(int cardNumber, int pin)
    {
        // Debit is valid if card and pin are even
        if (cardNumber % 2 == 0 && validPin(pin)) {
            return ++authNumCounter;
        }
        return -1;
    }
}