package com.example.asapd.MemberRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberContactRequest {

    @SerializedName("contact")
    @Expose
    private String contact;

    public MemberContactRequest(String contact){
        this.contact = contact;
    }
}
