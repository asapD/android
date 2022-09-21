package com.example.asapd;

import com.example.asapd.MemberRequest.MemberContactCodeRequest;
import com.example.asapd.MemberRequest.MemberContactRequest;
import com.example.asapd.MemberRequest.MemberEmailRequest;
import com.example.asapd.MemberRequest.MemberSignUpRequest;
import com.example.asapd.MemberRequest.MemberSigninRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
}
