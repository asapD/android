package com.example.asapd.MemberRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberEmailRequest {

    @SerializedName("email")
    @Expose
    private String email;

    public MemberEmailRequest (String email){
        this.email = email;
    }
}
