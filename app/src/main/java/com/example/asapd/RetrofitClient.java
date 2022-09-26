package com.example.asapd;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://172.30.1.13:8081/";

//    private static final String BASE_URL = "http://172.30.1.98:8081/";

    public static RetrofitAPI getApiService(){return getInstance().create(RetrofitAPI.class);} // api 콜

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
