package edu.ttu.cs.se.entity;

import java.util.ArrayList;
import java.util.Iterator;

import static edu.ttu.cs.se.entity.ItemEntity.COL_SEP;


/**
 * An Entity class representing one order. The class extends
 * an ArrayList of ItemEntity in order to allow to both work
 * with ItemEntity elements and still provide functionalities
 * of ArrayLists.
 *
 * @author N'Godjigui Junior Diarrassouba
 * created on 11/17/2018
 */
public class OrderEntity extends ArrayList<ItemEntity> {
    /**
     * The default row length (in terms of spaces) used
     * by the data when printed. It is calculated as follows
     * 3 * 25 for 3 zones which are 25 characters (spaces) long
     * + 5 for the column separators "|" as defined in ItemEntity and statically imported here
     * + 4 for the first zone which contains the printed item number on the printed list.
     */
    public static final Integer ROW_LENGTH = 3 * 25 + 5 + 4;

    /**
     * A StringBuilder instance holding the current order's string representation.
     */
    private StringBuilder stringRep;

    /**
     * The sum of prices of all the ItemEntity in the given OrderEntity.
     */
    private Double subtotal;

    /**
     * Public constructor for the class which makes use of a function to (re)set the state
     * of the current OrderEntity.
     */
    public OrderEntity() {
        super();

        initialize();
    }

    /**
     * Initializes both the internal storage to 0 and the stringBuilder with
     * the header, which should always be printed.
     */
    private void initialize() {
        this.subtotal = 0.0;
        buildStringRep();
    }

    /**
     * Sets the internal StringBuilder to a brand new StringBuilder which has a clean header.
     */
    private void buildStringRep() {
        StringBuilder sb = new StringBuilder();
        String[] cols = new String[]{"Item #", "Items", "Description", "Price(in $)"};

        //Build header
        for (String i : cols) {
            if (i.equals(cols[0])) {
                sb.append(String.format("%-6s", i));
            } else {
                sb.append(String.format("%-25s", i));
            }
            sb.append(COL_SEP);
        }

        sb.append("\n");

        for (int i = 0; i < ROW_LENGTH; i++) {
            sb.append("-");
        }

        sb.append(COL_SEP);


        this.stringRep = sb.append("\n");
    }

    /**
     * Adds items to the ArrayList using <code>this.add(ItemEntity)</code>.
     *
     * <p>The function will only add an item if the item does not contain alcohol, or the
     * item contains alcohol but is authorized to be added.</p>
     *
     * @param item the item to be added.
     * @param isAuthorized a boolean representing whether an item is authorized to be added or not
     * @return boolean value signifying the success of the add operation.
     *         returns <code>true</code> if either one of the conditions detailed above are met.
     */
    public Boolean addItem(ItemEntity item, Boolean isAuthorized) {
        if (!item.getAlcohol() || isAuthorized) {
            this.add(item);
            subtotal += item.getPrice();

            this.stringRep.append(String.format("%-6s", String.valueOf(this.size())));
            this.stringRep.append(COL_SEP);
            this.stringRep.append(item.toString());
            this.stringRep.append("\n");
            return true;
        } else {
            return false;
        }
    }

    /**
     * Removes the item in the printed items list at the specified position.
     *
     * @param itemNum the integer position of the item to be removed.
     */
    public void removeItem(Integer itemNum) {
        subtotal -= this.get(itemNum).getPrice();
        stringRep.setLength(0);
        buildStringRep();
        Integer index = 1;

        Iterator<ItemEntity> it = this.iterator();
        for (int i = 1; it.hasNext(); i++) {
            if (i != itemNum) {
                this.stringRep.append(String.format("%-6s", String.valueOf(index)));
                this.stringRep.append(COL_SEP);
                this.stringRep.append(it.next().toString());
                this.stringRep.append("\n");
                index += 1;
            } else {
                it.next();
                it.remove();
            }
        }
    }

    /**
     * Returns the subtotal's value.
     *
     * @return the value of the current sum of all items.
     */
    public Double getSubtotal() {
        return subtotal;
    }

    /**
     * Returns the StringBuilder representation of an order as a String.
     * @return the current order as a string.
     */
    @Override
    public String toString() {
        return stringRep.toString();
    }

    /**
     * Resets the state of the current order.
     */
    public void flush() {
        stringRep.setLength(0);
        initialize();
    }
}
