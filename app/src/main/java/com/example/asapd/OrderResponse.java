package com.example.asapd;

import java.util.HashMap;
import java.util.Objects;

public class OrderResponse {
    private final Integer status;

    private String message;

    private HashMap<String, String> data;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public OrderResponse(Integer status, String message,HashMap<String, String> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
