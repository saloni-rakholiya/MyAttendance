package com.example.my_attendance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class forthird extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE=200;
    private static final int STORAGE_REQUEST_CODE=400;
    private static final int IMAGE_PICK_GALLERY=1000;
    private static final int IMAGE_PICK_CAMERA=1001;
   String filename;

    EditText resultet;
    Button savef;
    ImageView mpreview;
    EditText whichfie,fromfile;
    Button retrieve;

    String cameraperm[];
    String storageperm[];


    Uri img_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forthird);

        resultet=findViewById(R.id.resulted);
        mpreview=findViewById(R.id.imgiv);
        savef=findViewById(R.id.savefile);

        whichfie=findViewById(R.id.namefile);
        fromfile=findViewById(R.id.retrieve);
        retrieve=findViewById(R.id.loadfile);

        cameraperm=new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};

        storageperm=new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        savef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filename=whichfie.getText().toString();
                if(filename!=null)
                {save(v);
                whichfie.getText().clear();}
                else Toast.makeText(forthird.this,"Enter file name",3000).show();

            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filename=fromfile.getText().toString();
                if(filename!=null)
               { load(v);
                fromfile.getText().clear();}
                else Toast.makeText(forthird.this,"Enter file name",3000).show();

            }
        });


    }

    public void save(View v)
    {

        String txt=resultet.getText().toString();
        if(txt!=null)

       { FileOutputStream fos=null;
        try {
            fos=openFileOutput(filename,MODE_PRIVATE);
            fos.write(txt.getBytes());
            resultet.getText().clear();
            Toast.makeText(this,"File saved to "+getFilesDir()+"/"+filename,3000).show();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null)
            {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }}
        else {Toast.makeText(this,"Enter text",3000).show();}

    }

    public void load(View v)
    {
        FileInputStream fis=null;
        try {
            fis=openFileInput(filename);

            InputStreamReader isr=new InputStreamReader(fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String teext;

            while((teext=br.readLine())!=null)
            {

                sb.append(teext).append("\n");

            }
            resultet.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.addimg)
        {
            showimgimportdialog();
        }
        else if(R.id.settings==id)
        {

        }
        return super.onOptionsItemSelected(item);
    }

    private void showimgimportdialog() {
        String[] items={"Camera","Gallery"};
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("Select Image");
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which==0){

                    if(!checkCameraPermission())
                    {
                        requestCamerapermission();
                    }
                    else pickcam();
                }
                    else if(which==1)
                {
                    if(!checkStoragePermission())
                    {
                        requestStoragepermission();
                    }
                    else pickgallery();

                }
            }
        });
        dialog.create().show();
    }

    private void pickgallery() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_GALLERY);

    }


    private void pickcam() {
    ContentValues values=new ContentValues();
    values.put(MediaStore.Images.Media.TITLE,"NewPic");
    values.put(MediaStore.Images.Media.DESCRIPTION,"Image to text");
    img_uri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

    Intent cameraintent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,img_uri);
    startActivityForResult(cameraintent,IMAGE_PICK_CAMERA);

    }

    private void requestStoragepermission() {
        ActivityCompat.requestPermissions(this,storageperm,STORAGE_REQUEST_CODE);

    }

    private boolean checkStoragePermission() {
        boolean result=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);

        return result;
    }

    private void requestCamerapermission() {
        ActivityCompat.requestPermissions(this,cameraperm,CAMERA_REQUEST_CODE);
        

    }

    private boolean checkCameraPermission() {

        boolean result= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED);

        boolean result1=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED);



        return result && result1;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case CAMERA_REQUEST_CODE:
                if(grantResults.length>0)
                   { boolean cameraaccepted= grantResults[0]==
                            PackageManager.PERMISSION_GRANTED;
                    boolean writestorageaccepted= grantResults[0]==
                        PackageManager.PERMISSION_GRANTED;
                    if(cameraaccepted && writestorageaccepted )
                    {
                        pickcam();
                    }
                   else {
                        Toast.makeText(this,"Permission denied",3000).show();

                    }}
                break;
            case STORAGE_REQUEST_CODE:
            {
                boolean writestorageaccepted= grantResults[0]==
                        PackageManager.PERMISSION_GRANTED;
                if(writestorageaccepted )
                {
                    pickcam();
                }
                else {
                    Toast.makeText(this,"Permission denied",3000).show();

                }} break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK)
        {
            if(requestCode==IMAGE_PICK_GALLERY){
                CropImage.activity(data.getData())
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
            }
            if(requestCode==IMAGE_PICK_CAMERA){
                CropImage.activity(img_uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }
        }
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(resultCode==RESULT_OK){
                Uri resulturi=result.getUri();
                mpreview.setImageURI(resulturi);

                BitmapDrawable bitmapDrawable=(BitmapDrawable)mpreview.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();

                TextRecognizer recognizer=new TextRecognizer.Builder(getApplicationContext()).build();


                if(!recognizer.isOperational())
                {
                    Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Frame frame=new Frame.Builder().setBitmap(bitmap).build();

                    SparseArray<TextBlock> items=recognizer.detect(frame);
                    StringBuilder sb=new StringBuilder();
                    for(int i=0;i<items.size();++i)
                    {

                        TextBlock myitems=items.valueAt(i);
                        sb.append(myitems.getValue());
                        sb.append("\n");
                    }

                    resultet.setText(sb.toString());

                }
            }
            else if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                Exception error=result.getError();
                Toast.makeText(this,""+error,Toast.LENGTH_SHORT).show();
            }
        }
    }
}


