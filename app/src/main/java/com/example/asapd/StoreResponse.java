package com.example.asapd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreResponse {

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

    public StoreResponse(Integer status, String message, HashMap<String, Object> data){
        this.status = status;
        this.message = message;
        this.data = data;
    }
}

