package com.example.mysms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.util.Patterns.PHONE;

public class Main3Activity extends AppCompatActivity {
    EditText e6,e7;
    Button b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        b3=findViewById(R.id.btn3);
        e6=findViewById(R.id.et6);
        e7=findViewById(R.id.et7);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.SEND_SMS);
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    mymsg();
                } else {
                    ActivityCompat.requestPermissions(Main3Activity.this, new String[]{Manifest.permission.SEND_SMS}, 0);
                }

            }
        });
    }
    private void mymsg(){

        String phno=e6.getText().toString().trim();
        String msg=e7.getText().toString().trim();
        if(!e6.getText().toString().equals("") || !e7.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phno, null, msg, null, null);
            Toast.makeText(Main3Activity.this, "Message sent successfully to "+phno, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Main3Activity.this,"Please enter number and msg",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if(grantResults.length >= 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    mymsg();
                }
                else{
                    Toast.makeText(Main3Activity.this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
