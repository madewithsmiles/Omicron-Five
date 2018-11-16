package edu.ttu.cs.se.entity;

public class ItemEntity {
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
}
