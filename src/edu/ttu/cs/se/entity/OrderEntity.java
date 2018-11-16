package edu.ttu.cs.se.entity;

import java.util.HashMap;

import static edu.ttu.cs.se.entity.ItemEntity.COL_SEP;

public class OrderEntity {
    private HashMap<Integer, ItemEntity> itemList = null;
    private final Integer ROW_LENGTH = 3 * 25 + 5 + 4;
    private StringBuilder stringRep;
    private Double subtotal;

    public OrderEntity() {
        initialize();
    }

    private void initialize() {
        this.itemList = new HashMap<>();
        this.subtotal = 0.0;

        StringBuilder sb = new StringBuilder();
        String[] cols = new String[]{"Item #","Items","Description","Price(in $)"};

        //Build header
        for(String i: cols) {
            if (i.equals(cols[0])) {
                sb.append(String.format("%-4s",i));
            } else {
                sb.append(String.format("%-25s",i));
            }
            sb.append(COL_SEP);
        }

        this.stringRep = sb.append("\n");
    }

    public void addItem(ItemEntity item) {
        itemList.put(itemList.size()+1,item);
        subtotal += item.getPrice();

        this.stringRep.append(String.valueOf(itemList.size()));
        this.stringRep.append(item.toString());
        this.stringRep.append("\n");
    }

    public void removeItem(Integer itemNum) {
        subtotal -= itemList.get(itemNum).getPrice();
        itemList.remove(itemNum);
        stringRep.replace(itemNum * ROW_LENGTH, ROW_LENGTH, "");
    }

    @Override
    public String toString() {
        return stringRep.toString();
    }
}
