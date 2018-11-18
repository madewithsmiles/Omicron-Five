package edu.ttu.cs.se.applogic;

import edu.ttu.cs.se.entity.InventoryEntity;
import edu.ttu.cs.se.entity.ItemEntity;
import edu.ttu.cs.se.entity.OrderEntity;
import edu.ttu.cs.se.entity.TransactionLogEntity;
import edu.ttu.cs.se.systeminterface.BankInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Business Logic encapsulating methods for checkout logic
 *
 * @author N'Godjigui Junior Diarrassouba
 * created on 11/17/2018
 */
public class CheckoutLogic {
    /**
     * Tax rate set as a final variable for the program lifetime.
     */
    private final static Double TAX_RATE = 0.04;

    /**
     * The name of the file where to find the employees IDs.
     */
    private static final String EIN_DATA_NAME = "employeeID.csv";

    /**
     * The OrderEntity variable held by the CheckoutLogic.
     * Every order is stored in this variable.
     */
    private static OrderEntity currentOrder = null;

    /**
     * A StringBuilder variable holding the receipt as a string
     * ready to be sent to the CheckoutUI. It also allows
     * fast string manipulation.
     */
    private static StringBuilder receipt;

    /**
     * A String holding the last four digits which need to be
     * printed whenever the client pays with a card.
     */
    private static String lastFourDigits = null;

    /**
     * An ArrayList holding the valid employee ids.
     */
    private static ArrayList<String> employeeIDs = null;

    /**
     * A boolean variable indicating whether an item
     * containing alcohol can be authorized or not.
     */
    private static Boolean isAuthorized;

    /**
     * A boolean variable which indicates whether a
     * variable was paid by card.
     */
    private static Boolean paidByCard;

    /**
     * The class's default constructor.
     */
    public CheckoutLogic() {

    }

    /**
     * Initializes various variables and populate
     * the employees ids' ArrayList.
     */
    public static void initializeLogic() {
        receipt = new StringBuilder();
        isAuthorized = false;
        paidByCard = false;
        populateEIN();
    }

    /**
     * Populates the ArrayList of employee ids.
     */
    private static void populateEIN() {
        String line;
        String separator = ",";
        String cwd = System.getProperty("user.dir");
        String osFiSep= System.getProperty("file.separator");
        String file_loc = cwd + osFiSep + "src" + osFiSep + "edu" + osFiSep + "ttu"
                + osFiSep + "cs" + osFiSep + "se" + osFiSep + "data" + osFiSep + EIN_DATA_NAME;

        employeeIDs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file_loc))) {

            while ((line = br.readLine()) != null) {

                String[] info = line.split(separator);
                if(!info[0].equals("employeeID")) {
                    // Populate the list with parsed input
                    employeeIDs.add(info[0]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates order by initializing the OrderEntity
     * with a new OrderEntity.
     */
    public static void createOrder() {
        currentOrder = new OrderEntity();
    }

    /**
     * Retrieves the current order as a
     * specially formatted String
     *
     * @return an OrderEntity
     */
    public static String getOrderContent() {
        return currentOrder.toString();
    }

    /**
     * Adds an item and returns true if the item was added.
     *
     * For an item to be added. The item needs to be either
     * 1) an item which does not contain Alcohol
     * 2) an item contains alcohol but is authorized to be
     *    added (requires employee ID).
     *
     * @param item the ItemEntity element being added to the Order
     * @return whether an item was added
     */
    public static Boolean addItem(String item) {
        return currentOrder.addItem(InventoryEntity.getItem(item),isAuthorized);
    }

    /**
     * Returns the subtotal of the current order.
     * Subtotal = Total - (Subtotal * Tax applied).
     *
     * @return the subtotal, using the calculation above.
     */
    public static Double getSubTotal() {
        return currentOrder.getSubtotal();
    }

    /**
     * Returns the total of the current order.
     * Total = Subtotal + (Subtotal * Tax applied).
     *
     * @return the total, using the calculation above.
     */
    public static Double getOrderTotal() {
        return TAX_RATE * currentOrder.getSubtotal() + currentOrder.getSubtotal();
    }

    /**
     * Returns whether the cash payment was received.
     *
     * Checks to see whether the amount is equal or greater than
     * or equals to the amount to be made.
     *
     * @param amount cash inserted by the client.
     * @return true if the payment was received.
     */
    public static Boolean payByCash(Double amount) {
        Double total = getOrderTotal();
        return amount.equals(total) || amount > total;
    }

    /**
     * Returns whether the credit card payment was received.
     *
     * <p>Sends the card number to the bank interface for authorization.
     * Sets the paidByCard variable to true if the payment was
     * authorized and retrieves the last four digits for the receipt.</p>
     *
     * @param cardNum card number of the user's credit card.
     * @return true if the payment was received.
     */
    public static Boolean payByCredit(Integer cardNum) {
        if (BankInterface.authorizeCredit(cardNum) == -1) {
            return false;
        }
        paidByCard = true;
        String cardAsString = String.valueOf(cardNum);
        setLastFourDigits(cardAsString.substring(cardAsString.length() - 4));
        return true;
    }

    /**
     * Returns whether the debit card payment was received.
     *
     * <p>Sends the card number to the bank interface for authorization.
     * Sets the paidByCard variable to true if the payment was
     * authorized and retrieves the last four digits for the receipt.</p>
     *
     * @param cardNum the debit card number of the client.
     * @param pin the pin number of the debit card of the client.
     * @return <code>true</code> if the payment was received.
     */
    public static Boolean payByDebit(Integer cardNum, Integer pin) {
        if (BankInterface.authorizeDebit(cardNum, pin) == -1) {
            return false;
        }
        paidByCard = true;
        String cardAsString = String.valueOf(cardNum);
        setLastFourDigits(cardAsString.substring(cardAsString.length() - 4));
        return true;
    }

    /**
     * Stores the last four digits of the card number.
     *
     * @param cardNum the card number.
     */
    private static void setLastFourDigits(String cardNum) {
        lastFourDigits = cardNum;
    }

    /**
     * Resets the order variable whenever the current order.
     * is cancelled. Allows reuse.
     */
    public static void flushOrder() {
        currentOrder.flush();
    }

    /**
     * Finalize the order and sends back the receipt.
     *
     * <p><code>processOrder</code> decreases the quantity of each
     * items in the inventory by 1. Then the receipt gets printed
     * using the receipt StringBuilder private field. Checks
     * whether the payment was made using a card to include the last
     * four digits on the receipt. Then, the transaction log is updated
     * and it is also sent the order's total. Finally, all the variables
     * are reset.</p>
     * @return the receipt of the order.
     */
    public static String processOrder() {
        for (ItemEntity it: currentOrder) {
            InventoryEntity.removeItems(it.getName(),1);
        }

        receipt.append("Receipt:\n");
        for (int i = 0; i < OrderEntity.ROW_LENGTH; i++) {
            receipt.append("-");
        }
        receipt.append("\n");
        receipt.append(currentOrder.toString());

        if(paidByCard) {
            receipt.append("LAST FOUR DIGITS OF CARD: ").append(lastFourDigits);
        }

        for (int i = 0; i < OrderEntity.ROW_LENGTH; i++) {
            receipt.append("-");
        }

        TransactionLogEntity.incTransactions();
        TransactionLogEntity.addRevenue(getOrderTotal());

        String output = receipt.toString();

        lastFourDigits = null;
        receipt.setLength(0);
        paidByCard = false;


        return output;
    }

    /**
     * Checks to see whether the entered employee ID is valid.
     *
     * @param ein the entered employee ID.
     * @return <code>true</code> if the employee ID is valid.
     */
    public static Boolean verifyEmployeeID(String ein) {
        isAuthorized = employeeIDs.contains(ein);
        return isAuthorized;
    }
}