package com.example.my_attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class studpage extends AppCompatActivity {

    Button forclass,manager,third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studpage);

        forclass=findViewById(R.id.button13);
        manager=findViewById(R.id.button14);
        third=findViewById(R.id.button15);

        forclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(studpage.this,scanner.class);
                startActivity(intent);
            }
        });

        manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(studpage.this,managerr.class);
                startActivity(intent2);

            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(studpage.this,forthird.class);
                startActivity(intent3);
            }
        });



    }
}
