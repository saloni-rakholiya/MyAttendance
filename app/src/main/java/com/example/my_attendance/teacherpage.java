package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class teacherpage extends AppCompatActivity {

    Button btn;
    Button btn10;
    Button logout6;

    Button newclass;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherpage);



        newclass=findViewById(R.id.button8);
        btn=findViewById(R.id.button3);

        btn10=findViewById(R.id.button11);
        firebaseAuth=FirebaseAuth.getInstance();
       // final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        //final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        firebaseDatabase=FirebaseDatabase.getInstance();
        final String mystringtopass=firebaseAuth.getCurrentUser().getUid();





        newclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(teacherpage.this,addclass.class);
                intent.putExtra("id",mystringtopass);
                startActivity(intent);

            }
        });


        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(teacherpage.this,allclasseslist.class);
                startActivity(intent);

            }
        });


        logout6=findViewById(R.id.button6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(teacherpage.this,generatecode.class);
                startActivity(intent);
            }
        });

        logout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                startActivity(new Intent(teacherpage.this,teacherlogin.class));
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            /*case R.id.logout:{
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(teacherpage.this,teacherlogin.class));


            }*/
            case R.id.refresh:{
                Intent intent=new Intent(teacherpage.this,teacherpage.class);
                startActivity(intent);
            }


        }

        return true;
    }

    public static class studentslist {
    }
}
