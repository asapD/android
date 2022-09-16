package com.example.asapd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SignUp_accept extends AppCompatActivity {

    private CheckBox chk_service_accept, chk_service_location, chk_personal_info, chk_check_all;
    private Button btn_gosignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_accept);

        chk_service_accept = findViewById(R.id.chk_service_accept);
        chk_service_location = findViewById(R.id.chk_service_location);
        chk_personal_info = findViewById(R.id.chk_personal_info);
        chk_check_all = findViewById(R.id.chk_check_all);

        btn_gosignup = findViewById(R.id.btn_gosignup);
        btn_gosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chk_service_accept.isChecked() && chk_service_location.isChecked() && chk_personal_info.isChecked()){
                    Intent intent = new Intent(getApplicationContext(), SignUp_input.class);
                    startActivity(intent);
                }
            }
        });

    }
}