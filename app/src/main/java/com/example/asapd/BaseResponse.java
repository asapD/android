package com.example.asapd;

public class BaseResponse {


    private final Integer status;

    private String message;

    private Object data;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public BaseResponse(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
