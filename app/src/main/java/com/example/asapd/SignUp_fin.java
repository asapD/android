package com.example.asapd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.asapd.ui.login.LoginActivity;

public class SignUp_fin extends AppCompatActivity {

    private Button btn_signup_to_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_fin);

        btn_signup_to_login = findViewById(R.id.btn_signup_to_login);
        btn_signup_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP); // 메인 페이지로 돌아가면서, 이전 액티비티 스택들 비움
                startActivity(intent);
            }
        });
    }
}