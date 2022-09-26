package com.example.asapd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemData implements Serializable {
    double itemId;
    String name;
    String description;
    double price;
    double storeId;

    public ItemData(double itemId, String name, String description, double price, double storeId){
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.storeId = storeId;
    }

    public double getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public double getStoreId() {return storeId;}
    public void setStoreId(int storeId) { this.storeId = storeId; }

    public static ArrayList<ItemData> createList(int num){
        ArrayList<ItemData> list = new ArrayList<ItemData>();

        for(int i = 1; i <= num; i++){
            list.add(new ItemData(i,"item" + i, "content"+i, 1000+i, i));
        }
        return list;
    }
}