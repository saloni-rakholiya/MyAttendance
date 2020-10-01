package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class passwordteacher extends AppCompatActivity {
    private EditText email;
    private Button btn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordteacher);

        firebaseAuth=FirebaseAuth.getInstance();

        email=findViewById(R.id.editText7);
        btn=findViewById(R.id.button7);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail=email.getText().toString().trim();

                if(useremail.equals(""))
                {
                    Toast.makeText(passwordteacher.this,"PLease enter registered email id",3000).show();

                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(passwordteacher.this,"Password reset email sent",3000).show();
                                finish();
                                startActivity(new Intent(passwordteacher.this,teacherlogin.class));

                            }else{
                                Toast.makeText(passwordteacher.this,"Error in sending mail!",3000).show();
                            }
                        }
                    });
                }
            }
        });


    }
}
