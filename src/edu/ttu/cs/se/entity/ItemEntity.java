package edu.ttu.cs.se.entity;

public class ItemEntity {
    public static final String COL_SEP = "|";
    private static final String INDICATOR = "...";
    private static final Integer OUT_LENGTH = 25;
    private String name;
    private Double price;
    private Boolean alcohol;
    private String desc;
    private Double discount;

    public ItemEntity(String name, Double price, Boolean alcohol, String desc, Double discount) {
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.alcohol = alcohol;
        this.discount = discount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        Double newPrice = price + price * discount;
        return newPrice;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Boolean getAlcohol() {
        return this.alcohol;
    }

    public void setAlcohol(Boolean alcohol) {
        this.alcohol = alcohol;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    private String prepareString(String a, Integer length) {
        Integer endIndex = a.length() + INDICATOR.length();
        String preparedString;

        if (endIndex <= length) {
            preparedString = a;//.concat(INDICATOR);
        } else {
            preparedString = a.substring(
                    0,
                    length - INDICATOR.length()
                                ).concat(INDICATOR);
        }

        return preparedString;
    }

    public String getString(Boolean dispName, Boolean dispDesc, Boolean dispPrice, Boolean dispAlcohol, Boolean dispDiscount) {
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

    @Override
    public String toString() {
        return getString(true,true,true, false,false);
    }
}
