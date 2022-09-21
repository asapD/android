package com.example.asapd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                String contact = et_phone.getText().toString();
                MemberContactRequest memberContactRequest = new MemberContactRequest(contact);
                sendCertificate(memberContactRequest);
            }
        });

        btn_fin_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((et_email == null) ||(et_email.length() == 0) ||(et_pw .length() == 0) || (et_name .length() == 0) || (et_phone.length() == 0)){
                    Toast.makeText(getApplicationContext(), "please enter all information",Toast.LENGTH_SHORT).show();
                } else {
                    String email = et_email.getText().toString();
                    String pw = et_pw.getText().toString();
                    String name = et_name.getText().toString();
                    String contact = et_phone.getText().toString();
                    MemberSignUpRequest memberSignUpRequest = new MemberSignUpRequest(name,contact,email,pw);
                    startSignUp(memberSignUpRequest);
                }
            }
        });


    }

    private void startSignUp(MemberSignUpRequest memberSignUpRequest) {
        RetrofitClient.getApiService().userSignUp(memberSignUpRequest).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse result = response.body();
                if (result.getStatus() == 200) {
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

    private void sendCertificate(MemberContactRequest memberContactRequest){
        RetrofitClient.getApiService().sendSMS(memberContactRequest).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse result = response.body();
                if (result.getStatus() == 200) {
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