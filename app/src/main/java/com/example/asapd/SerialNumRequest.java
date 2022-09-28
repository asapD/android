package com.example.asapd;

import android.content.SharedPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SerialNumRequest {

    @SerializedName("serialNum")
    @Expose
    private String serialNum;

    @SerializedName("orderId")
    @Expose
    private Long orderId;

    public SerialNumRequest(String serialNum, Long orderId) {
        this.serialNum = serialNum;
        this.orderId = orderId;
    }
}
