package com.example.asapd.MemberRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberSigninRequest {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public MemberSigninRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
