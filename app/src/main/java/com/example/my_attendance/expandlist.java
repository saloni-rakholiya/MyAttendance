package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.Vector;

public class expandlist extends AppCompatActivity {

    DatabaseReference mdatabaseref;
    DatabaseReference mdatabaseref2;
    private TextView head;
    private Button notice;
    private Button btn;
    private TextView t21;
    private ListView muserlist;
    private Vector<String> vect=new Vector<String>();
    String s2;
    String thisoneforcode="";
    private ArrayList<String> musernames=new ArrayList<>();

    private EditText edt;
DatabaseReference dr;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandlist);

        notice=findViewById(R.id.button12);

        t21=findViewById(R.id.textView21);

        firebaseAuth=FirebaseAuth.getInstance();
        head=findViewById(R.id.textView18);
        t21.setMovementMethod(new ScrollingMovementMethod());

        Intent intent=getIntent();
        final String s=intent.getStringExtra("mystring");

        String id=firebaseAuth.getCurrentUser().getUid();
        dr=FirebaseDatabase.getInstance().getReference().child("classes").child(id).child(s);

        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(expandlist.this, noticee.class);
                intent.putExtra("id",s);
                startActivity(intent);
            }
        });


        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String keystore = ds.getKey();
                    if (keystore.equals("code")) {
                        thisoneforcode = ds.getValue().toString();
                        //Toast.makeText(expandlist.this,thisoneforcode,4000).show();
                        break;
                    }

                }
                    //here
                    {

                        if (!thisoneforcode.equals("")) {
                            mdatabaseref2 = FirebaseDatabase.getInstance().getReference().child("classes").child(s).child(thisoneforcode);
                            // head.setText("smthingigggggggggggggggggggggggg");

                            mdatabaseref2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    for (DataSnapshot ds : snapshot.getChildren()) {
                                        // String key=ds.getKey();
                                        //t21.append(key);
                                        //vect.add(key);
                                        //t21.append("\n");
                                        String xy = ds.getKey();
                                        if(!xy.equals("students"))
                                      {  t21.append(ds.getValue().toString());
                                        t21.append("\n");}
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            Toast.makeText(expandlist.this, "OOPS EMPTY", 3000).show();
                        }
                    }
                    //tohere

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





       /* mdatabaseref= FirebaseDatabase.getInstance().getReference().child("classes").child(s);


        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //String name=snapshot.getValue().toString();
                //head.append(name);

                for (DataSnapshot ds : snapshot.getChildren()) {
                    vect.add(ds.getKey().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        for(int i=0;i<vect.size();++i)
            Log.d("tagg",vect.get(i));*/




     /*   {

            if (!thisoneforcode.equals("")) {
                mdatabaseref2 = FirebaseDatabase.getInstance().getReference().child("classes").child(s).child(thisoneforcode);
                // head.setText("smthingigggggggggggggggggggggggg");

                mdatabaseref2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        for (DataSnapshot ds : snapshot.getChildren()) {
                            // String key=ds.getKey();
                            //t21.append(key);
                            //vect.add(key);
                            //t21.append("\n");
                            String xy = ds.getKey();
                            t21.append(xy);
                            t21.append("\n");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            } else {
                Toast.makeText(expandlist.this, "OOPS EMPTY", 3000).show();
            }
        }*/

       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s2=edt.getText().toString();
                if(vect.contains(s2))
                {
                Intent intent=new Intent(expandlist.this,studlist.class);

                intent.putExtra("heading",s2);
                intent.putExtra("class",s);
                startActivity(intent);}
                else {
                    Toast.makeText(expandlist.this, "Not a code",3000).show();
                }
            }
        });
*/


       // Toast.makeText(expandlist.this,s,3000).show();










    }
}
