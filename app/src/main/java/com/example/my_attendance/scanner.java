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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

public class scanner extends AppCompatActivity {

    public static TextView t;
    int x;
    Button logout,notice;
    Button btn;
    Vector<String> vect=new Vector<String>();
    DatabaseReference firebaseDatabase;
    FirebaseAuth firebaseAuth;
    EditText classname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        t=findViewById(R.id.textView7);

        classname=findViewById(R.id.editText10);
        btn=findViewById(R.id.button2);
        notice=findViewById(R.id.button3);

        logout=findViewById(R.id.button10);
       /* String sendtonextactivity=classname.getText().toString();*/

        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("classes");
        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String key=ds.getKey();
                    vect.add(key);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendtonextactivity=classname.getText().toString();
                Intent intent=new Intent(scanner.this,readnotice.class);
                intent.putExtra("class",sendtonextactivity);
                startActivity(intent);
            }
        });




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(scanner.this,studentlogin.class);
                startActivity(intent);
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String sendtonextactivity=classname.getText().toString();

              if(vect.contains(sendtonextactivity))
              {

                Intent intent=new Intent(scanner.this,camscan.class);
                intent.putExtra("thename",sendtonextactivity);
                startActivity(intent);

              }
              else {
                  Toast.makeText(scanner.this,"Not a class",3000).show();
              }


            }
        });
    }

}
