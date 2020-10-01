package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

public class addclass extends AppCompatActivity {

    EditText ed;
    Button btn;
    Vector<String > x=new Vector<String>();
    EditText code;
    String s="";
    DatabaseReference mdatabaseref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addclass);
        btn=findViewById(R.id.button9);
        ed=findViewById(R.id.editText8);

        code=findViewById(R.id.editText9);


        //here
        mdatabaseref= FirebaseDatabase.getInstance().getReference().child("classes");
        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key=ds.getKey();
                    x.add(key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //to here

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=ed.getText().toString();
                String forcode=code.getText().toString();



                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                classs classss=new classs(s);

                String forid=getIntent().getStringExtra("id");


                //Toast.makeText(addclass.this,x.firstElement(),3000).show();

                //Toast.makeText(addclass.this,forid,3000).show();

                if(!x.contains(s))
               { firebaseDatabase.getInstance().getReference("classes")
                        .child(forid)
                        .child(s)
                        .child("code")
                        .setValue(forcode);

                firebaseDatabase.getInstance().getReference("classes")
                        .child(forid)
                        .child(s)
                        .child("name")
                        .setValue(s);

                firebaseDatabase.getInstance().getReference("classes")
                        .child(s)
                        .child(forcode)
                        .child("students")
                        .push()
                        .setValue("sampledude");




                /*firebaseDatabase.getInstance().getReference("classes")
                        .child(s)
                        .child(forcode)
                        .child("students")
                        .push()
                        .setValue("sampledude");*/
                /*firebaseDatabase.getInstance().getReference("classes")
                        .child(forid)
                        .child(s)
                        .child(forcode)
                        .child("sample")
                .setValue("studentname");*/

                //firebaseDatabase.getInstance().getReference("classes")
                  //      .child(s)
                    //    .child(forcode)
                      //  .child("stud1")
                        //.setValue("name");




        }
                else
                {Toast.makeText(addclass.this,"ALREADY EXISTS",3000).show();}


    }
});}




    }
