package com.example.my_attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class generatecode extends AppCompatActivity {
    String TAG="generateqrcode";

    EditText edt;
    ImageView qrimg;
    Button btn;
    Bitmap bitmap;
    String input;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generatecode);

        edt=findViewById(R.id.editText3);
        qrimg=findViewById(R.id.imageView4);
        btn=findViewById(R.id.button4);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input=edt.getText().toString().trim();
                if(input.length()>0)
                {
                    WindowManager manager=(WindowManager)getSystemService(WINDOW_SERVICE);
                    Display display=manager.getDefaultDisplay();
                    Point point=new Point();
                    display.getSize(point);
                    int width=point.x;
                    int height=point.y;
                    int smaller=width<height ? width:height;
                    smaller=smaller*3/4;
                    qrgEncoder=new QRGEncoder(input,null, QRGContents.Type.TEXT,smaller);
                    try {
                        bitmap=qrgEncoder.encodeAsBitmap();
                        qrimg.setImageBitmap(bitmap);

                    }

                    catch(WriterException e){
                        Log.v(TAG,e.toString());

                }}
                else {
                    edt.setError("Required");

                }

            }
        });



    }
}
