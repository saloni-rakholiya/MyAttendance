package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;

import java.lang.ref.Reference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class camscan extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ImageView btn14;
    ImageView btn15;
    String setname="";
    Vector<String> vect=new Vector<String>();
    DatabaseReference databaseReference;
    ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);


      /*  final String sendtonextactivity=getIntent().getStringExtra("thename");
        Toast.makeText(camscan.this,sendtonextactivity,3000).show();*/


        if (ContextCompat.checkSelfPermission(camscan.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)
          {  setContentView(R.layout.activity_camscan);
          btn14=findViewById(R.id.imageView14);
          btn15=findViewById(R.id.imageView15);


          btn15.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  setContentView(scannerView);
              }
          });

          btn14.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(Settings.ACTION_APPLICATION_SETTINGS));
                  //startActivity(new Intent(Settings.ACTION_SETTINGS));
              }
          });

          }
        else
       { setContentView(scannerView);}


    }

    @Override
    public void handleResult(final Result result) {

        final FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        final String name=getIntent().getStringExtra("thename");



{


    Toast.makeText(camscan.this, "SUCCESSFULLY SCANNED", 3000).show();


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    //here
    String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
    //Toast.makeText(camscan.this,currentTime,4000).show();
    //to here

    String name1 = user.getEmail() + " " + currentDate + " " + currentTime;


    firebaseDatabase.getInstance().getReference("classes")
            .child(name)
            .child(result.getText())
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(name1);


    // DatabaseReference myref=firebaseDatabase.getReference(firebaseAuth.getUid());
      /*  firebaseDatabase.getInstance().getReference("classes")
                .child(result.getText())
                .child("student")
        .setValue("MEEEJOJO");*/


    // Toast.makeText(camscan.this,firebaseAuth.getCurrentUser().getUid(),5000).show();
    // DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("student").child(firebaseAuth.getCurrentUser().getUid());

        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setname=snapshot.child("name").getValue().toString();
                presentabsentclass teacherclass=new presentabsentclass(setname);


                firebaseDatabase.getInstance().getReference("classes")
                        .child(name)
                        .child(result.getText())
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(teacherclass);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        /*presentabsentclass teacherclass=new presentabsentclass(setname);


      firebaseDatabase.getInstance().getReference("classes")
              .child(name)
              .child(result.getText())
              .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
              .setValue(teacherclass);*/


}
//else {Toast.makeText(camscan.this,"Doesn't exist",3000).show();}

//scanner.t.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
