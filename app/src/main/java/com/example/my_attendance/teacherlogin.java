package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class teacherlogin extends AppCompatActivity {

    TextView t;

    FirebaseAuth firebaseAuth;
    TextView t13;
    private int counter=5;
    private EditText username;
    private EditText password;
private  int checker=0;
    private ProgressDialog progressDialog;
    Editable forusername;
    Editable forpassword;

    private TextView forgot;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlogin);

        /*//here
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        Toast.makeText(teacherlogin.this,currentTime,4000).show();
        //to here*/


        t=findViewById(R.id.textView6);
        btn=findViewById(R.id.button);

        t13=findViewById(R.id.textView13);
        username=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);

        forgot=findViewById(R.id.textView14);
        t13.setText("Number of attempts left:"+String.valueOf(counter));

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        progressDialog=new ProgressDialog(this);

/*
        if(user!=null)
        {

            //finish();
         //   Intent intent=new Intent(teacherlogin.this,teacherpage.class);
         //   startActivity(intent);
        }*/



        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(teacherlogin.this,passwordteacher.class));
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forusername=username.getText();
                forpassword=password.getText();

                //if(forusername.equals("") || forpassword.equals("") )
                if(TextUtils.isEmpty(forpassword) || TextUtils.isEmpty(forusername))
                {
                    Toast.makeText(teacherlogin.this,"LOGIN FAILED",3000).show();
                }
                else
                {validate(username.getText().toString(),password.getText().toString());}


              //  Intent intent=new Intent(teacherlogin.this,teacherpage.class);
              //  startActivity(intent);
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(teacherlogin.this,newteacherlogin.class));
            }
        });




    }
    private void validate(String username,String password)
    {
        progressDialog.setMessage("Verifying your details");
        progressDialog.show();
//here



        //here
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {


                   //putlater progressDialog.dismiss();
                   // Toast.makeText(teacherlogin.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                   // Intent intent=new Intent(teacherlogin.this,teacherpage.class);
                    //startActivity(intent);

                    final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("teacher");
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String value=String.valueOf(snapshot.child("type").getValue());
                            //checker=0;
                            if(value.equals("teacher"))
                            {//checker=1;
                                progressDialog.dismiss();
                                checkverificaton(); }
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(teacherlogin.this,"Not A teacher",3000).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.dismiss();
                            Toast.makeText(teacherlogin.this,"OOPS",3000).show();


                        }
                    });

                    //checkverificaton();
                }
                else{ progressDialog.dismiss();
                    Toast.makeText(teacherlogin.this,"LOGIN FAILED",Toast.LENGTH_SHORT).show();

                    counter=counter-1;
                    t13.setText("Number of attempts left:"+String.valueOf(counter));
                    if(counter==0)
                    {
                        btn.setEnabled(false);
                    }
                }

            }
        });

    }
    private void checkverificaton()
    {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        final Boolean emailflag=firebaseUser.isEmailVerified();


        if(emailflag)
        { finish();
            startActivity(new Intent(teacherlogin.this,teacherpage.class));

        }
        else {
            Toast.makeText(this,"Verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
