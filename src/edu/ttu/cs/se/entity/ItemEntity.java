package edu.ttu.cs.se.entity;

/**
 * Entity storing the information of an item in inventory.
 *
 * @author N'Godjigui Junior Diarrassouba
 * created on 11/17/2018
 */
public class ItemEntity {
    /**
     * String variable to separate info columns.
     */
    public static final String COL_SEP = "|";

    /**
     * String variable holding the indicator to be used
     * when a name field or a description field has a length
     * greater than OUT_LENGTH.
     */
    private static final String INDICATOR = "...";

    /**
     * Specifies the length in terms of spaces of the output
     * of the private fields.
     */
    private static final Integer OUT_LENGTH = 25;

    /**
     * The name of the item.
     */
    private String name;

    /**
     * The price of the item.
     */
    private Double price;

    /**
     * Whether an item contains alcohol.
     */
    private Boolean alcohol;

    /**
     * The description of the item.
     */
    private String desc;

    /**
     * The discount to be applied on the price of the item.
     */
    private Double discount;

    /**
     * The ItemEntity's parameterized constructor using the required info.
     *
     * @param name the name of the item.
     * @param price the price of the item.
     * @param alcohol whether the item contains alcohol.
     * @param desc the description of the item.
     * @param discount the discount to be applied on the item.
     */
    public ItemEntity(String name, Double price, Boolean alcohol, String desc, Double discount) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.alcohol = alcohol;
        this.discount = discount;
    }

    /**
     * Gets the name of the item.
     *
     * @return the name private field.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the item.
     *
     * @return the price private field.
     */
    public Double getPrice() {
        Double newPrice = price + price * discount;
        return newPrice;
    }

    /**
     * Sets the price of the item.
     *
     * @param price the new price.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the description of the item.
     *
     * @return the description of the item.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Sets the description private field.
     *
     * @param desc the new description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Gets whether the item contains alcohol.
     *
     * @return the alcohol private field.
     */
    public Boolean getAlcohol() {
        return this.alcohol;
    }

    /**
     * Sets the alcohol boolean private field.
     *
     * @param alcohol the new status.
     */
    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    /**
     * Gets the discount.
     *
     * @return the discount private field.
     */
    public Double getDiscount() {
        return this.discount;
    }

    /**
     * Sets the discount of the item.
     *
     * @param discount the new discount.
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * Reduces the length of the string to the specified length and append
     * the INDICATOR whenever the length of the string is longer than the
     * specified length.
     *
     * @param a the string to change.
     * @param length the specified length the string should not exceed.
     * @return the changed string.
     */
    private String prepareString(String a, Integer length) {
        Integer endIndex = a.length() + INDICATOR.length();
        String preparedString;

        if (endIndex <= length) {
            preparedString = a;
        } else {
            preparedString = a.substring(
                    0,
                    length - INDICATOR.length()
                                ).concat(INDICATOR);
        }

        return preparedString;
    }

    /**
     * A special method to retrieve specific elements from the ItemEntity.
     *
     * @param dispName whether to include the name in the output.
     * @param dispDesc whether to include the description in the output.
     * @param dispPrice whether to include the price in the output.
     * @param dispAlcohol whether to include whether the item has alcohol.
     * @param dispDiscount whether to include the discount in the output.
     * @return the string with the specified information.
     */
    public String getString(Boolean dispName, Boolean dispDesc, Boolean dispPrice,
                            Boolean dispAlcohol, Boolean dispDiscount) {
        StringBuilder sb = new StringBuilder();
        String[] info = new String[]{
                prepareString(String.valueOf(getName()), OUT_LENGTH),
                prepareString(String.valueOf(getDesc()), OUT_LENGTH),
                String.valueOf(getPrice()),
                String.valueOf(getAlcohol()),
                String.valueOf(getDiscount())};

        for (int i = 0; i < info.length; i++) {

            if ((i==0 && !dispName) || (i==1 && !dispDesc) || (i == 2 && !dispPrice)
                    || (i == 3 && !dispAlcohol) || (i == 4 && !dispDiscount))
                continue;

            sb.append(String.format("%-25s",info[i]));
            sb.append(COL_SEP);

        }

        return sb.toString();
    }

    /**
     * Overrides the default <code>toString</code> function by calling the custom
     * <code>getString</code>.
     *
     * It returns a prepared string which includes the name, the description and the price
     * but does not include the alcohol and discount private field.s
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return getString(true,true,true, false,false);
    }
}
