package edu.ttu.cs.se.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static edu.ttu.cs.se.entity.ItemEntity.COL_SEP;

public class OrderEntity extends ArrayList<ItemEntity>{
    public static final Integer ROW_LENGTH = 3 * 25 + 5 + 4;
//    private ArrayList<ItemEntity> itemList = null;
    private StringBuilder stringRep;
    private Double subtotal;

    public OrderEntity() {
        super();

        initialize();
    }

    private void initialize() {
//        this.itemList = new ArrayList<>();
        this.subtotal = 0.0;
        buildStringRep();
    }

    private void buildStringRep() {
        StringBuilder sb = new StringBuilder();
        String[] cols = new String[]{"Item #","Items","Description","Price(in $)"};

        //Build header
        for(String i: cols) {
            if (i.equals(cols[0])) {
                sb.append(String.format("%-6s",i));
            } else {
                sb.append(String.format("%-25s",i));
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

    public Boolean addItem(ItemEntity item, Boolean isAuthorized) {
        if (item.getAlcohol() && isAuthorized || !item.getAlcohol()) {
            this.add(item);
            subtotal += item.getPrice();

            this.stringRep.append(String.format("%-6s",String.valueOf(this.size())));
            this.stringRep.append(COL_SEP);
            this.stringRep.append(item.toString());
            this.stringRep.append("\n");
            return true;
        }
        else {
            return false;
        }
    }

    // TODO: Talk to guys about this below. HIHGLY INEFFICIENT, ew
    public void removeItem(Integer itemNum) {
        subtotal -= this.get(itemNum).getPrice();
        stringRep.setLength(0);
        buildStringRep();
        Integer index = 1;

        Iterator<ItemEntity> it = this.iterator();
        for (int i = 1; it.hasNext() ; i++) {
            if (i != itemNum) {
                this.stringRep.append(String.format("%-6s",String.valueOf(index)));
                this.stringRep.append(COL_SEP);
                this.stringRep.append(it.next().toString());
                this.stringRep.append("\n");
                index += 1;
            } else {
                it.next();
                it.remove();
            }
        }
//        itemList.remove(itemNum);
//        Integer startIndex = 3 + itemNum * ROW_LENGTH * 2;
//        Integer endIndex = startIndex + ROW_LENGTH + 2;
//
//        while (startIndex > this.stringRep.length())
//            startIndex -= 1;
//            endIndex = this.stringRep.length()-1;
//
//        stringRep.replace(startIndex, endIndex, "");
    }

    public Double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return stringRep.toString();
    }

    public void flush() {
        stringRep.setLength(0);
        initialize();
    }
}
