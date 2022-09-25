package com.example.asapd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreData {
    double storeId;
    String name;
    double owner;
    String address;

    public StoreData(double storedId, String name, double owner, String address) {
        this.storeId = storedId;
        this.name= name;
        this.owner = owner;
        this.address = address;
    }

    public double getStoreId() { return storeId; }

    public double getOwner() { return owner; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = address;
    }


}
