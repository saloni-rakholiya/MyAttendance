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

public class studentlogin extends AppCompatActivity {

    TextView t;

    FirebaseAuth firebaseAuth;
    TextView t13;
    private int counter=5;
    private EditText username;
    private EditText password;

    String forpassword;
    String forusername;

    private ProgressDialog progressDialog;

    private TextView forgot;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        t=findViewById(R.id.textView6);
        btn=findViewById(R.id.button);

        t13=findViewById(R.id.textView13);
        username=findViewById(R.id.editText);
        password=findViewById(R.id.editText2);

        forgot=findViewById(R.id.textView14);
        t13.setText("Number of attempts left:"+String.valueOf(counter));

        firebaseAuth= FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        progressDialog=new ProgressDialog(this);


    /*    if(user!=null)
        {

            //finish();
            //   Intent intent=new Intent(teacherlogin.this,teacherpage.class);
            //   startActivity(intent);
        }*/


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(studentlogin.this,passwordteacher.class));
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forpassword=password.getText().toString();
                forusername=username.getText().toString();


                if(TextUtils.isEmpty(forpassword) || TextUtils.isEmpty(forusername))
                {
                    Toast.makeText(studentlogin.this,"LOGIN FAILED",3000).show();
                }
                else
               { validate(username.getText().toString(),password.getText().toString());}


                //  Intent intent=new Intent(teacherlogin.this,teacherpage.class);
                //  startActivity(intent);
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(studentlogin.this,newstudentlogin.class));
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


                   // progressDialog.dismiss();
                    // Toast.makeText(teacherlogin.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    // Intent intent=new Intent(teacherlogin.this,teacherpage.class);
                    //startActivity(intent);

                    final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("student");
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String value=String.valueOf(snapshot.child("type").getValue());
                            if(value.equals("student"))
                            {progressDialog.dismiss();
                            checkverificaton();}
                            else{
                                progressDialog.dismiss();
                                Toast.makeText(studentlogin.this,"Not A Student",3000).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            progressDialog.dismiss();
                            Toast.makeText(studentlogin.this,"OOPS",3000).show();


                        }
                    });


                }
                else{ progressDialog.dismiss();
                    Toast.makeText(studentlogin.this,"LOGIN FAILED",Toast.LENGTH_SHORT).show();

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
        Boolean emailflag=firebaseUser.isEmailVerified();


        if(emailflag)
        { finish();
            startActivity(new Intent(studentlogin.this,studpage.class));

        }
        else {
            Toast.makeText(this,"Verify your email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}




