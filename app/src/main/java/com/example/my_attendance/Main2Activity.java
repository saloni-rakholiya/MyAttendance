package com.example.my_attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    ImageView teacher;
    ImageView contact;
    ImageView student;
    ImageView instructions;
    Animation scaleup,scaledown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        contact=findViewById(R.id.imageView16);
        instructions=findViewById(R.id.imageView12);
        teacher=findViewById(R.id.imageView2);
        student=findViewById(R.id.imageView3);

        scaledown= AnimationUtils.loadAnimation(this,R.anim.scaledown);
        scaleup=AnimationUtils.loadAnimation(this,R.anim.scaleup);




        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencontact();
            }
        });





        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teacher.startAnimation(scaleup);
                teacher.startAnimation(scaledown);
                Intent intent=new Intent(Main2Activity.this,teacherlogin.class);
                startActivity(intent);
            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                student.startAnimation(scaleup);
                student.startAnimation(scaledown);
                Intent intent2=new Intent(Main2Activity.this,studentlogin.class);
                startActivity(intent2);
            }
        });


    }

    public void opendialog()
    {

        exampledialog exm=new exampledialog();

        exm.show(getSupportFragmentManager(),"exampledialog");
    }

    public void opencontact()
    {
        forcontact fr=new forcontact();
        fr.show(getSupportFragmentManager(),"forcontact");

    }

}
