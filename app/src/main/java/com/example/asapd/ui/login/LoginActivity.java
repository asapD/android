package com.example.asapd.ui.login;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;


import com.example.asapd.HomePageActivity;
import com.example.asapd.R;
import com.example.asapd.SignUp_accept;


public class LoginActivity extends AppCompatActivity {

    private EditText et_userid, et_userpw;
    private Button btn_signin, btn_signup;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_userid = findViewById(R.id.et_userid);
        et_userpw = findViewById(R.id.et_userpw);

        et_userid.setOnEditorActionListener(new TextView.OnEditorActionListener() { // 엔터 입력 시 처리
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i==EditorInfo.IME_ACTION_DONE || i==EditorInfo.IME_NULL)
                    return true;
                return false;
            }
        });

        et_userpw.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i==EditorInfo.IME_ACTION_DONE || i==EditorInfo.IME_NULL){

                    // 로그인 시도()

                    return true;
                }
                return false;
            }
        });

        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);


                // 서버 통신


                startActivity(intent);
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
}