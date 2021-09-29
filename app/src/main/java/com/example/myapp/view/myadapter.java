package com.example.myapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Moods;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.adapterHolder> {
    private ArrayList<Moods> myList;
    private Context context;
    LayoutInflater inflater;



    public myadapter(ArrayList<Moods> myList, Context context) {
        inflater=LayoutInflater.from(context);
        this.myList = myList;
        this.context = context;
    }


    @NonNull
    @Override
    public adapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.listview,parent,false);
        return new adapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterHolder holder, int position) {
       Moods currentMoods=myList.get(position);
        holder.setData(currentMoods,position);

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class adapterHolder extends RecyclerView.ViewHolder{
        TextView days;
        ImageView image7;
        public adapterHolder(@NonNull View itemView) {
            super(itemView);

            days=(TextView) itemView.findViewById(R.id.days);
            image7=(ImageView) itemView.findViewById(R.id.image7);


        }

        public void setData(Moods currentMoods, int position) {
            this.days.setText(currentMoods.getComment());
            this.image7.setImageResource(currentMoods.getImage());
            this.days.setBackgroundColor(currentMoods.getColors());




        }
    }



}
