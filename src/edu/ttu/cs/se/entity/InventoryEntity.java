package edu.ttu.cs.se.entity;

import javafx.util.Pair;

import java.util.HashMap;

public class InventoryEntity {
    private HashMap<String, Pair<ItemEntity, Integer>> items = null;

    public Integer getQuantity(String name) {
        return items.get(name).getValue();
    }

    public void addItems(String name, Integer qty) {
    }

    public void removeItems(String name, Integer qty) {

    }

    public ItemEntity getItem(String name) {
        return items.get(name).getKey();
    }
}
