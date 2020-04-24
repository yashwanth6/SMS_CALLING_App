package com.example.mysms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    Button b2;
    TextView t8;
    dbhelper db;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name="yash";
    public static final String password="yashwanth";
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.et4);
        et2=findViewById(R.id.et5);
        b2=findViewById(R.id.btn2);
        t8=findViewById(R.id.tx8);
        db=new dbhelper(MainActivity.this);
        sp=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        if (sp.contains(name)) {
            et1.setText(sp.getString(name,""));
        }
        if (sp.contains(password)) {
            et2.setText(sp.getString(password, ""));

        }
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s3=et1.getText().toString();
                String s4=et2.getText().toString();
                SharedPreferences.Editor editor=sp.edit();
                editor.putString(name,s3);
                editor.putString(password,s4);
                editor.commit();



                Boolean chkemailpassword=db.emailpassword(s3,s4);
                if(chkemailpassword==true){
                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,Main4Activity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"wrong email or password",Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(MainActivity.this, s3+"\n"+s4, Toast.LENGTH_SHORT).show();

            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
    }
}
