package com.example.asapd;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("api/auth/sign-up")
    Call<BaseResponse> userSignUp(@Body MemberSignUpRequest memberSignUpRequest);

}
