package com.example.asapd;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemResponse {
    private final Integer status;
    private String message;
    private HashMap<String, ArrayList> data;

    public Integer getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public HashMap<String, ArrayList> getData() {
        return data;
    }

    public ItemResponse(Integer status, String message, HashMap<String, ArrayList> data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
