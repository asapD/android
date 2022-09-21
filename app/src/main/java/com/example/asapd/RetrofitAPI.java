package com.example.asapd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("api/auth/sign-up")
    Call<BaseResponse> userSignUp(@Body MemberSignUpRequest memberSignUpRequest);

    @POST("api/auth/verify-contact")
    Call<BaseResponse> sendSMS(@Body MemberContactRequest memberContactRequest);

    @POST("api/auth/sign-in")
    Call<BaseResponse> userLogin(@Body MemberSigninRequest memberSigninRequest);

}
