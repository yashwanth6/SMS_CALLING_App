package com.example.mysms;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main2Activity extends AppCompatActivity {

    EditText e1, e2, e3, e4;
    dbhelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button b  =  findViewById(R.id.btn);
        e1 = findViewById(R.id.et1);
        e2 = findViewById(R.id.et2);
        e3 = findViewById(R.id.et3);
        e4 = findViewById(R.id.et4);
        db=new dbhelper(Main2Activity.this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validation()){
                    //Toast.makeText(Main2Activity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    String s1=e1.getText().toString();
                    String s2=e2.getText().toString();
                    String s3=e3.getText().toString();
                    String s4=e4.getText().toString();
                    if(s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") ) {
                        Toast.makeText(Main2Activity.this,"fields empty",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean checkmail=db.checkmail(s4);
                        if(checkmail == true) {
                            Boolean insert = db.insert(s1,s4,s2,s3);
                            if (insert == true ) {
                                Toast.makeText(Main2Activity.this, "registered successfully", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(Main2Activity.this,MainActivity.class);
                                startActivity(i);
                            }
                        }
                        else {
                            Toast.makeText(Main2Activity.this,"email already exists",Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }



        });

    }
    public  boolean validation() {
        String s2 = e2.getText().toString();
        String s3=e3.getText().toString();
        String s4 = e4.getText().toString();
        if (!isValidEmail(s4)) {
            e4.setError("Invalid email");
            Toast.makeText(Main2Activity.this, "Invalid email", Toast.LENGTH_SHORT).show();
        }

        if (!isValidPass(s2)) {
            e2.setError("Invalid password");
            Toast.makeText(Main2Activity.this, "Invalid password", Toast.LENGTH_SHORT).show();

        }
        if(!isValidNum(s3)){
            e3.setError("Invalid number");
            Toast.makeText(Main2Activity.this, "Invalid number", Toast.LENGTH_SHORT).show();
        }
        if(isValidEmail(s4)==true && isValidPass(s2)==true && isValidNum(s3)==true){
            return true;
        }
        return false;
    }
    private boolean isValidEmail(String s4) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(s4);
        return matcher.matches();
    }
    private boolean isValidPass(String s2) {
        if (s2 != null && s2.length() > 6) {
            return true;
        }
        return false;
    }
    private boolean isValidNum(String s3){
        if(s3.length()==10){
            return true;
        }
        return false;
    }

};





