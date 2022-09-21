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

import com.example.asapd.MemberRequest.MemberContactCodeRequest;
import com.example.asapd.MemberRequest.MemberContactRequest;
import com.example.asapd.MemberRequest.MemberEmailRequest;
import com.example.asapd.MemberRequest.MemberSignUpRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp_input extends AppCompatActivity {
    private EditText et_name,et_pw, et_re_pw, et_email, et_phone, et_authCode;
    private Button btn_fin_signup, btn_email_check, btn_send_code, btn_code_check;
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
        btn_email_check = findViewById(R.id.btn_email_check);
        btn_send_code = findViewById(R.id.btn_send_code);
        btn_code_check = findViewById(R.id.btn_code_check);

        et_phone.setInputType(InputType.TYPE_CLASS_PHONE);
        et_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());


        btn_email_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이메일 중복 체크
                String email = et_email.getText().toString();
                MemberEmailRequest memberEmailRequest = new MemberEmailRequest(email);
                checkEmail(memberEmailRequest);
            }
        });

        btn_send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 전화번호 인증 요청 to server
                String contact = et_phone.getText().toString();
                MemberContactRequest memberContactRequest = new MemberContactRequest(contact);
                sendCode(memberContactRequest);
            }
        });

        btn_code_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인증코드 일치 체크
                String contact = et_phone.getText().toString();
                String authcode = et_authCode.getText().toString();
                MemberContactCodeRequest memberContactCodeRequest = new MemberContactCodeRequest(contact, authcode);
                checkCode(memberContactCodeRequest);
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

      private void checkEmail(MemberEmailRequest memberEmailRequest){
        RetrofitClient.getApiService().verifyEmail(memberEmailRequest).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse result = response.body();
                if (result.getStatus() == 200) {
                    Log.d("TAG", result.getMessage());
                }
                else if (result.getStatus() == 409) {
                    Log.d("TAG", result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("Send code Error", t.getMessage());
                t.printStackTrace();
            }
        });
      }

    private void sendCode(MemberContactRequest memberContactRequest){
        RetrofitClient.getApiService().verifyContact(memberContactRequest).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse result = response.body();
                if (result.getStatus() == 200) {
                    Log.d("TAG", result.getMessage());
                }
                else if (result.getStatus() == 500) {
                    Log.d("TAG", result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("Send code Error", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void checkCode(MemberContactCodeRequest memberContactCodeRequest){
        RetrofitClient.getApiService().verifyContactCode(memberContactCodeRequest).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse result = response.body();
                if (result.getStatus() == 200) {
                    Log.d("TAG", result.getMessage());
                }
                else if (result.getStatus() == 403) {
                    Log.d("TAG", result.getMessage());
                }
                else if (result.getStatus() == 400) {
                    Log.d("TAG", result.getMessage());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.e("Code check Error", t.getMessage());
                t.printStackTrace();
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
                else if (result.getStatus() == 500) {
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