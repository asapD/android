package com.example.asapd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.asapd.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationActivity extends AppCompatActivity {

    private Button btn_true, btn_false;
    private SharedPreferences preferences, pref_serialNum;
    private String serialNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        btn_true = findViewById(R.id.btn_true);
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pref_serialNum = getSharedPreferences("serialNum", MODE_PRIVATE);
                serialNum = pref_serialNum.getString("serialNum", "NULL");
                SerialNumRequest serialNumRequest = new SerialNumRequest("asapD2022090431", 3L);
                checkSerialNum(serialNumRequest);
            }
        });
    }

    private void checkSerialNum(SerialNumRequest serialNumRequest){
        RetrofitClient.getApiService().verifySerialNum(preferences.getString("TOKEN", "NULL") ,serialNumRequest).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                Log.d("TAG", preferences.getString("TOKEN", "NULL"));
                OrderResponse result = response.body();
                Log.d("TAG", result.getStatus().toString());
                Log.d("TAG", result.getMessage());
                if(result.getStatus() == 200) {
                    Log.d("TAG", result.getMessage());
                }
                else{
                    Log.d("TAG", result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });
    }
}