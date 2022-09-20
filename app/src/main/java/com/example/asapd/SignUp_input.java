package com.example.asapd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp_input extends AppCompatActivity {
    private EditText et_name, et_id, et_pw, et_re_pw, et_birth, et_email, et_phone, et_authCode;
    private Button btn_fin_signup, btn_send_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_input);

        et_name = findViewById(R.id.et_name);
        et_pw = findViewById(R.id.et_pw);
        et_re_pw = findViewById(R.id.et_re_pw);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_authCode = findViewById(R.id.et_authCode);

        btn_fin_signup = findViewById(R.id.btn_fin_signup);
        btn_send_code = findViewById(R.id.btn_send_code);

        et_phone.setInputType(InputType.TYPE_CLASS_PHONE);
        et_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());


        btn_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 전화번호 인증 요청 to server


            }
        });

        btn_fin_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUp_fin.class); // 회원가입 완료 화면으로 이동
                startActivity(intent);
            }
        });



    }


}