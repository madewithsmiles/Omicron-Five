package edu.ttu.cs.se.applogic;

import java.util.Scanner;

public class IOHelper {

    private static void displayMessage(String message)
    {
        System.out.print(message + ": ");
    }

    public static String getInputString(String message)
    {
        Scanner scanner = new Scanner(System.in);
        displayMessage(message);
        String rawInput = scanner.nextLine();
        return rawInput.toLowerCase().trim();
    }

    public static Integer getInputInt(String message)
    {
        Scanner scanner = new Scanner(System.in);
        displayMessage(message);
        String rawInput = scanner.nextLine();
        return Integer.parseInt(rawInput.trim());
    }

    public static Double getInputDouble(String message)
    {
        Scanner scanner = new Scanner(System.in);
        displayMessage(message);
        String rawInput = scanner.nextLine();
        return Double.parseDouble(rawInput.trim());
    }

}
