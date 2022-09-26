package com.example.asapd;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemResponse {
    private final Integer status;
    private String message;
    private HashMap<String, Object> data;

    public Integer getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public HashMap<String, Object> getData() {
        return data;
    }

    public ItemResponse(Integer status, String message, HashMap<String, Object> data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
