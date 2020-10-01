package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class newteacherlogin extends AppCompatActivity {
    private EditText name,email,password;
    private Button btn;
    private TextView login;
    String username,userpassword,useremail;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newteacherlogin);
        setupUIViews();

        firebaseAuth=FirebaseAuth.getInstance();




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate())
                {

                    String user_email=email.getText().toString().trim();
                    String user_password=password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {



                            if(task.isSuccessful()) {
                               // Toast.makeText(newteacherlogin.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(newteacherlogin.this,afterlogin.class));

                                sendemail();
                              //  checkverificaton();

                            }
                            else{
                                Toast.makeText(newteacherlogin.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });


                    //upload
                }


            }
        });




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(newteacherlogin.this,teacherlogin.class));
            }
        });


    }


    private void setupUIViews(){

        name=findViewById(R.id.editText4);
        email=findViewById(R.id.editText5);
        password=findViewById(R.id.editText6);
        btn=findViewById(R.id.button5);
        login=findViewById(R.id.textView12);

    }

    private Boolean validate()
    {
        Boolean result=false;
         username=name.getText().toString();
        userpassword=password.getText().toString();
         useremail=email.getText().toString();

        if(useremail.isEmpty() || username.isEmpty() || userpassword.isEmpty())
        {
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }
        else {result=true;}

        return result;

    }

    private void checkverificaton()
    {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag=firebaseUser.isEmailVerified();


        if(emailflag)
        { finish();
            startActivity(new Intent(newteacherlogin.this,teacherpage.class));

        }
        else {
            Toast.makeText(this,"Verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }

    private  void sendemail()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser!=null)
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        senduserdata();
                        Toast.makeText(newteacherlogin.this,"Successfully registered,Verification email sent!",Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(newteacherlogin.this,teacherlogin.class));

                    }
                    else{
                        Toast.makeText(newteacherlogin.this,"Verification mail not sent!",Toast.LENGTH_SHORT).show();

                    }
                }
            });
    }

    private void senduserdata()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        teacherclass teacherclass=new teacherclass("teacher",username,useremail);

       // DatabaseReference myref=firebaseDatabase.getReference(firebaseAuth.getUid());
        firebaseDatabase.getInstance().getReference("teacher")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(teacherclass);



        //myref.setValue(teacherclass);


    }

}
