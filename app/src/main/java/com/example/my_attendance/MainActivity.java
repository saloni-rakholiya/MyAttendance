package com.example.my_attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topanim,bottomanim;

    ImageView img;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    topanim= AnimationUtils.loadAnimation(this,R.anim.topanim);
    bottomanim=AnimationUtils.loadAnimation(this,R.anim.bottomanim);
    img=findViewById(R.id.imageView);
    txt=findViewById(R.id.heading);

    img.setAnimation(topanim);
    txt.setAnimation(bottomanim);

    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
            finish();
        }
    },2000);


    }
}
