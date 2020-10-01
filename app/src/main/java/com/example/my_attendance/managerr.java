package com.example.my_attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class managerr extends AppCompatActivity {

    Button btn;
    EditText total2,attended2,needed2;
    int attended,total;
    Double percentage;
    Double x;
    int y;
    TextView text;

    PieChart pieChart;
    String f[]={"Classes Attended","Classes needed"};
    int num[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managerr);
       attended2= findViewById(R.id.editText12);
        total2=findViewById(R.id.editText13);
        needed2=findViewById(R.id.editText14);
        btn=findViewById(R.id.button16);
        text=findViewById(R.id.textView26);
        pieChart=findViewById(R.id.chartt);
        pieChart.setUsePercentValues(true);
        Description desc=new Description();
        desc.setText("Pie Chart");
        desc.setTextSize(20f);
        pieChart.setDescription(desc);
        pieChart.setHoleRadius(20f);
        pieChart.setTransparentCircleRadius(20f);


         final List<PieEntry> piedataset;
        piedataset=new ArrayList<>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piedataset.clear();

                attended=Integer.parseInt(attended2.getText().toString());
                total=Integer.parseInt(total2.getText().toString());
                percentage=Double.valueOf(needed2.getText().toString());
                if(attended<0 || total<=0 || percentage<=0 || percentage>100)
                {
                    Toast.makeText(managerr.this,"Enter Valid values",3000).show();

                }
                else {

                    x= (percentage*total/100);
                    y= (int) Math.ceil(x);

                    if(y<=attended) { piedataset.add(new PieEntry(0,"Remaining to attend"));
                    text.setText("No more classes needed.");}
                    else {  piedataset.add(new PieEntry(y-attended,"Remaining to attend"));
                        text.setText("You need to attend "+String.valueOf(y-attended)+" more classes.");}
                }


                piedataset.add(new PieEntry(attended,"Attended classes"));
                PieDataSet set=new PieDataSet(piedataset,"Attendance");
                set.setColors(ColorTemplate.JOYFUL_COLORS);

                PieData pieData=new PieData(set);

                pieChart.setData(pieData);
                pieChart.animateXY(1400,1400);
            }
        });

    }


}
