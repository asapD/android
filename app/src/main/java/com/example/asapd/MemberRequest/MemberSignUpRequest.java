package com.example.asapd.MemberRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberSignUpRequest {


    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    public MemberSignUpRequest(String name, String contact, String email, String password){
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.password = password;
    }
}
