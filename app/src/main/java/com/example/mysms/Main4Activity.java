package com.example.mysms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    ListView list;
    String[] maintitle ={
            "SMS","PhoneCall",
    };

    String[] subtitle ={
            "Messaging","call",
    };

    Integer[] imgid={
            R.drawable.ic_chat,R.drawable.ic_phone,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listview adapter=new listview(this, maintitle, subtitle,imgid);
        list=findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent i=new Intent(Main4Activity.this,Main3Activity.class);
                    startActivity(i);
                }
                else if(position==1){
                    Intent it=new Intent(Main4Activity.this,Main5Activity.class);
                    startActivity(it);
                }

            }
        });
    }
}
