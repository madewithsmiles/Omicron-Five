package edu.ttu.cs.se.applogic;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Logic class helper for input and output
 *
 * @author Ryan Kelley
 * created on 11/17/2018
 */
public class IOHelper {

    /**
     * Prints message to System.out formatted with a ':' character after it
     *
     * @param message the message to print to System.out
     */
    private static void displayMessage(String message) {
        System.out.print(message + ": ");
    }

    /**
     * Displays input prompt and validates user input as a string
     *
     * @param message message to prompt to user
     * @return the user's validated input
     */
    public static String getInputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMessage(message);
            String output = scanner.nextLine().toLowerCase().trim();
            if (output.length() > 0) {
                System.out.println();
                return output;
            } else {
                System.out.println("Invalid input. Empty strings are not allowed. Please try again.");
            }
        }
    }

    /**
     * Displays input prompt and validates user input as an integer
     *
     * @param message message to prompt to user
     * @param negativesAllowed are negative values valid input?
     * @return the user's validated input
     */
    public static Integer getInputInt(String message, boolean negativesAllowed)
    {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMessage(message);
            int output;
            try {
                output = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input. Integers only, please try again.");
                continue;
            }
            if (negativesAllowed || output >= 0) {
                System.out.println();
                return output;
            } else {
                System.out.println("Invalid input. Negatives are not allowed. Please try again.");
            }
        }
    }

    /**
     * Displays input prompt and validates user input as a double
     *
     * @param message message to prompt to user
     * @param negativesAllowed are negative values valid input?
     * @return the user's validated input
     */
    public static Double getInputDouble(String message, boolean negativesAllowed)
    {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMessage(message);
            double output;
            try {
                output = Double.parseDouble(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input. Decimal only, please try again.");
                continue;
            }
            if (negativesAllowed || output >= 0.0) {
                System.out.println();
                return output;
            } else {
                System.out.println("Invalid input. Negatives are not allowed. Please try again.");
            }
        }
    }

    /**
     * Displays input prompt and validates user input as int
     *
     * @param message message to prompt to user
     * @param min minimum valid input accepted
     * @param max maximum valid input accepted
     * @return user's validated input
     */
    public static Integer getCustomInt(String message, int min, int max) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMessage(message);
            int output;
            try {
                output = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input. Integers only, please try again.");
                continue;
            }
            if (output >= min && output <= max) {
                System.out.println();
                return output;
            } else {
                System.out.println(String.format("Invalid input. Input should be in range [%d, %d].",
                        min, max));
            }
        }
    }

    /**
     * Displays input prompt and validates user input as int.
     *
     * @param message message to prompt to user.
     * @param length how long input should be.
     * @return user's validated input.
     */
    public static Integer getCustomInt(String message, int length) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMessage(message);
            String raw = scanner.nextLine().toLowerCase().trim();
            if (raw.length() == length) {
                System.out.println();
                int output;
                try {
                    output = Integer.parseInt(raw);
                    return output;
                } catch (Exception e) {
                    System.out.println("Invalid input. Integers only, please try again.");
                    continue;
                }
            } else {
                System.out.println(String.format("Invalid input. Input length must be of length %d.", length));
            }
        }
    }

    /**
     * Prints a header before any menu.
     */
    public static void printHeader() {
        System.out.print("\n\n");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Prints a footer.
     */
    public static void printFooter() {
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Formats currency according to the Location. Should print $ in the US.
     *
     * @param num number to be formatted
     * @return formatted string
     */
    public static String formatCurrency(Double num) {
        return NumberFormat.getCurrencyInstance().format(num);
    }

}
