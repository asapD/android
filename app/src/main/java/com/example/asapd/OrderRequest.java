package com.example.asapd;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class OrderRequest {
    @SerializedName("destination")
    @Expose
    private String destination;

    @SerializedName("items")
    @Expose
    private HashMap<String, Integer> items;

    public OrderRequest(String destination, HashMap<String, Integer> items){
        this.destination = destination;
        this.items = items;
    }


}
