package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class noticee extends AppCompatActivity {

    EditText ed;
    String notice,classname;
    DatabaseReference databaseReference;
    String firebaseAuth;
    TextView list;
    int k=0;
    ImageView send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticee);

        ed=findViewById(R.id.edittext22);
        send=findViewById(R.id.img22);
        list=findViewById(R.id.textView24);


        firebaseAuth=FirebaseAuth.getInstance().getCurrentUser().getUid();

        classname=getIntent().getStringExtra("id");
        databaseReference=FirebaseDatabase.getInstance().getReference().child("classes").child(classname).child("Notices");

        k=0;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(k==1) list.setText("");
                for (DataSnapshot ds : snapshot.getChildren())
                {

                    String xyz=ds.getValue().toString();
                    list.append(xyz+"\n\n");

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=1;

                 notice=ed.getText().toString();
                 databaseReference.push().setValue(notice);
                 ed.setText("");

            }
        });




    }
}
