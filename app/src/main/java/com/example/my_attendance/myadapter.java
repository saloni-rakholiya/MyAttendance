package com.example.my_attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.MyViewHolder> {

    //Context context;
    ArrayList<cardforclassclass> classes;
    private OnNoteListener onNoteListener;


    public myadapter( ArrayList<cardforclassclass> p,OnNoteListener onNoteListener)
    {
       this.onNoteListener=onNoteListener;
       // context=c;
        this.classes=p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false),onNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nameofclass.setText(classes.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameofclass;
        OnNoteListener onNoteListener;



        public MyViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);

            nameofclass=itemView.findViewById(R.id.textView15);
            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onNoteListener.onNoteClick(v,getAdapterPosition());
        }
    }

//here

    public  interface OnNoteListener{
        void onNoteClick(View v,int position);
    }
    //to here



}
