package com.example.myapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Moods;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.adapterHolder> {
    private ArrayList<Moods> arrayItems;
    private Context context;
    LayoutInflater inflater;
    private RelativeLayout mLayout;






    public myadapter(ArrayList<Moods> arrayItems, Context context) {
        inflater=LayoutInflater.from(context);
        this.arrayItems = arrayItems;
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
       Moods currentMoods=arrayItems.get(position);
        holder.image7.setImageResource(currentMoods.getImage());
        holder.days.setBackgroundColor(currentMoods.getColors());
        holder.days.setText(currentMoods.getComment());







        if (arrayItems.get(position).getComment() != null){
            holder.image7.setVisibility(View.VISIBLE);
            final String comment = arrayItems.get(position).getComment();

            holder.image7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, comment, Toast.LENGTH_LONG).show();
                }
            });
        }else holder.image7.setVisibility(View.INVISIBLE);



    }

    @Override
    public int getItemCount() {
         return arrayItems.size();
    }

  static class adapterHolder extends RecyclerView.ViewHolder{
        TextView days;
        ImageView image7;
        public adapterHolder(@NonNull View itemView) {
            super(itemView);

            days= itemView.findViewById(R.id.days);
            image7= itemView.findViewById(R.id.image7);


        }
    }
    }



