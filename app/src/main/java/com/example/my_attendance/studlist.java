package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studlist extends AppCompatActivity {
    TextView t;
    String k="";
    DatabaseReference mdatabaseref;
    TextView scrolable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studlist);

        t = findViewById(R.id.textView19);

        scrolable = findViewById(R.id.textView20);
        scrolable.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        String heading = intent.getStringExtra("heading");
        String classname = intent.getStringExtra("class");
        t.setText(heading);

        mdatabaseref = FirebaseDatabase.getInstance().getReference().child("classes").child(classname).child(heading);
       /* //String name=snapshot.getValue().toString();
        //head.append(name);
        for (DataSnapshot ds : snapshot.getChildren()) {
            String key= (String) ds.getValue();
            scrolable.append(key);
            scrolable.append("\n");*/
        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //String name=snapshot.getValue().toString();
                //head.append(name);

                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key=ds.getKey();

                    if(!key.equals("students"))
                    { k=ds.getValue().toString();
                        scrolable.append(k);
                    //vect.add(key);
                    scrolable.append("\n");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



}}