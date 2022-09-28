package com.example.asapd;

import com.example.asapd.MemberRequest.MemberContactCodeRequest;
import com.example.asapd.MemberRequest.MemberContactRequest;
import com.example.asapd.MemberRequest.MemberEmailRequest;
import com.example.asapd.MemberRequest.MemberSignUpRequest;
import com.example.asapd.MemberRequest.MemberSigninRequest;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @POST("api/auth/sign-up")
    Call<BaseResponse> userSignUp(@Body MemberSignUpRequest memberSignUpRequest);

    @POST("api/auth/verify-contact")
    Call<BaseResponse> verifyContact(@Body MemberContactRequest memberContactRequest);

    @POST("api/auth/sign-in")
    Call<BaseResponse> userLogin(@Body MemberSigninRequest memberSigninRequest);

    @POST("api/auth/verify-email")
    Call<BaseResponse> verifyEmail (@Body MemberEmailRequest memberEmailRequest);

    @POST("api/auth/verify-contact-code")
    Call<BaseResponse> verifyContactCode (@Body MemberContactCodeRequest memberContactCodeRequest);

    @POST("api/orders")
    Call<OrderResponse> orderItems (@Header("Authorization") String authCode, @Body OrderRequest orderRequest);

    @GET("api/item")
    Call<ItemResponse> getItems (@Header("Authorization") String authCode);

    @GET("api/store")
    Call<StoreResponse> getStores (@Header("Authorization") String authCode);

    @POST("api/orders/verify-serial")
    Call<OrderResponse> verifySerialNum (@Header("Authorization") String authCode, @Body SerialNumRequest serialNumRequest);



}
