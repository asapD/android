package com.example.asapd;

import java.util.HashMap;

public class BaseResponse {


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

    public BaseResponse(Integer status, String message, HashMap<String, String> data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }

}
