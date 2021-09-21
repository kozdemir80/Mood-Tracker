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
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.adapterHolder> {
    private ArrayList<Moods> moodList;
    private Context context;

    public myadapter(ArrayList<Moods> moodList, Context context) {
        this.moodList = moodList;
        this.context = context;
    }

    public myadapter(List<Moods> list) {
    }

    @NonNull
    @Override
    public adapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.listview,parent,false);
        return new adapterHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterHolder holder, int position) {
   Moods myMoods = moodList.get(position);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class adapterHolder extends RecyclerView.ViewHolder{
        TextView days;
        ImageView image7;
        public adapterHolder(@NonNull View itemView) {
            super(itemView);

            days=(TextView) itemView.findViewById(R.id.days);
            image7=(ImageView) itemView.findViewById(R.id.image7);
        }

    }



}
