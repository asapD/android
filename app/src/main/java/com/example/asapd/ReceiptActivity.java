package com.example.asapd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReceiptActivity extends AppCompatActivity {

    private Button btn_plzHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        btn_plzHelp = findViewById(R.id.btn_plzHelp);
        btn_plzHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 파블로항공 CS팀 전화 연결로 띄우기
            }
        });

    }

}