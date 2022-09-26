package com.example.asapd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeActivity extends AppCompatActivity {

    private ImageView img_QRCode;
    private String serialNum;
    SharedPreferences pref_serialNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        pref_serialNum = getSharedPreferences("serialNum", MODE_PRIVATE);
        serialNum = pref_serialNum.getString("serialNum", "NULL");
        Log.d("TAG", "in QRActivity, serialNum : " + serialNum);

        img_QRCode = findViewById(R.id.img_QRCode);
//        serialNum = "This is Serial Number for 40 character"; // 아두이노에서 받을 시리얼 넘버

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(serialNum, BarcodeFormat.QR_CODE, 250, 250);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            img_QRCode.setImageBitmap(bitmap);
        }catch (Exception e){}



    }
}