package com.example.asapd.MemberRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberContactCodeRequest {

    @SerializedName("contact")
    @Expose
    private String contact;

    private String code;

    public MemberContactCodeRequest(String contact, String code){
        this.contact = contact;
        this.code = code;
    }
}
