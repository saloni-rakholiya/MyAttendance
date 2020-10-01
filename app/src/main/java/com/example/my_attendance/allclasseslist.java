package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class allclasseslist extends AppCompatActivity implements myadapter.OnNoteListener{

    DatabaseReference reference;
    private RecyclerView rv;

    FirebaseAuth firebaseAuth;
    private myadapter adapter;
    private ArrayList<cardforclassclass> a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allclasseslist);


        rv=findViewById(R.id.myrecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);


        firebaseAuth=FirebaseAuth.getInstance();
        a=new ArrayList<>();

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        reference= FirebaseDatabase.getInstance().getReference().child("classes").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               // for(DataSnapshot snapshot1:snapshot.getChildren())
                //{
                  //  cardforclassclass p=snapshot1.getValue(cardforclassclass.class);
                    //a.add(p);

                //}

                if(snapshot.exists())
                {
                    for(DataSnapshot nsnapshot: snapshot.getChildren())
                    {
                        cardforclassclass p=nsnapshot.getValue(cardforclassclass.class);
                        a.add(p);
                    }




                    adapter=new myadapter(a,allclasseslist.this);
                    rv.setAdapter(adapter);
                }
                else {
                   // Toast.makeText(allclasseslist.this,"no",4000).show();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(allclasseslist.this,"OOPS",4000).show();
            }
        });




    }

    @Override
    public void onNoteClick(View v,int position) {

        a.get(position);


        Intent intent=new Intent(allclasseslist.this,expandlist.class);
        intent.putExtra("mystring",String.valueOf(a.get(position).getName()));
        startActivity(intent);

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
           /* case R.id.logout:{
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(allclasseslist.this,teacherlogin.class));
                //finish();
            }*/
            case R.id.refresh:{
                Intent intent=new Intent(allclasseslist.this,allclasseslist.class);
                startActivity(intent);
            }


        }

        return true;
    }


}
