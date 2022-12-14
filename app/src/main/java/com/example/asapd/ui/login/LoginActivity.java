package com.example.asapd.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


import com.example.asapd.BaseResponse;
import com.example.asapd.HomePageActivity;
import com.example.asapd.MemberRequest.MemberSigninRequest;
import com.example.asapd.R;
import com.example.asapd.RetrofitClient;
import com.example.asapd.SignUp_accept;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private EditText et_userid, et_userpw;
    private Button btn_signin, btn_signup;
    private String ACCESSTOKEN;
    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPreferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = mPreferences.edit();

        et_userid = findViewById(R.id.et_userid);
        et_userpw = findViewById(R.id.et_userpw);


        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_userid.getText().toString();
                String pw = et_userpw.getText().toString();
                if(email == null || pw == null) { // "CHECK" email || pw 가 null인 경우 AlertDialog 띄우기
                }
                MemberSigninRequest memberSigninRequest = new MemberSigninRequest(email,pw);
                startSignIn(memberSigninRequest);
            }
        });

        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() { // 회원 가입 - 약관 동의 화면으로 전환
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp_accept.class);
                startActivity(intent);
            }
        });
    }

    public void startSignIn(MemberSigninRequest memberSigninRequest){
        RetrofitClient.getApiService().userLogin(memberSigninRequest).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse result = response.body();

                if (result.getStatus() == 200) {
                    Log.d("TAG", result.getMessage());
                    ACCESSTOKEN = result.getData().get("accessToken");
                    Log.d("TAG", "Token is : " + ACCESSTOKEN);

                    /* 토큰 SharedPreferences 로 관리 */
                    // getSharedPreferences()으로 접근 할 때, "TOKEN" 으로 불러 와야 함.
                    mPreferences = getSharedPreferences("pref", MODE_PRIVATE);
                    editor = mPreferences.edit();
                    editor.putString("TOKEN", ACCESSTOKEN);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                    startActivity(intent);
                }
                else if (result.getStatus() == 400) {
                    Log.d("TAG", result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("Sign up Error", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}