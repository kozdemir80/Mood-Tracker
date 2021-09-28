package com.example.myapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Moods;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.adapterHolder> {
    private ArrayList<Moods> setList;
    private Context context;



    public myadapter(ArrayList<Moods> setList, Context context) {
        this.setList = setList;
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
       Moods moods=setList.get(position);
        holder.setData(moods,position);
    }

    @Override
    public int getItemCount() {
        return setList.size();
    }

    class adapterHolder extends RecyclerView.ViewHolder{
        TextView days;
        ImageView image7;
        LinearLayout mLayout;
        public adapterHolder(@NonNull View itemView) {
            super(itemView);

            days=(TextView) itemView.findViewById(R.id.days);
            image7=(ImageView) itemView.findViewById(R.id.image7);
            mLayout= (LinearLayout) itemView.findViewById(R.id.mLayout);

        }

        public void setData(Moods moods, int position) {
            this.days.setText(moods.getComment());
            this.image7.setImageResource(R.drawable.comment);
            this.mLayout.setBackgroundResource(moods.getColors());

        }
    }



}
