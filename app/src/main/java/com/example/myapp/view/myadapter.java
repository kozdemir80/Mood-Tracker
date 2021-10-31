package com.example.myapp.view;

import static com.example.myapp.controller.MainActivity.moodColorsArray;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
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
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.adapterHolder> {
    private final List<Moods> arrayItems;
    private final Context context;
    LayoutInflater inflater;







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

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull adapterHolder holder, int position) {
        Moods myMood= arrayItems.get(position);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 240);


        try {

        TextView textView=holder.days;
        ImageView imageView= holder.image7;
        textView.setBackgroundColor(myMood.getColors(moodColorsArray));
        imageView.setImageResource(myMood.getImage());
            switch (position){
                case 0:
                    textView.setText("Today");
                    break;
                case 1:
                    textView.setText("Yesterday");
                    break;
                case 2:
                    textView.setText("2 days ago");
                    break;
                case 3:
                    textView.setText("3 days ago");
                    break;
                case 4:
                    textView.setText("4 days ago");
                    break;
                case 5:
                    textView.setText("5 days ago");
                    break;
                case 6:
                    textView.setText("6 days ago");
                    break;
            }
            switch (myMood.getWidth()){
                case 0:
                    params.width = width * 20 / 100;
                    break;
                case 1:
                    params.width = width * 40 / 100;
                    break;
                case 2:
                    params.width = width * 60 / 100;
                    break;
                case 3:
                    params.width = width * 80 / 100;
                    break;
                case 4:
                    params.width = width;
                    break;
            }
             holder.days.setLayoutParams(params);




        }catch (NullPointerException ignored){}


        try {


        if (arrayItems.get(position).getComment() != null){
            holder.image7.setVisibility(View.VISIBLE);
            final String comment = arrayItems.get(position).getComment();

            holder.image7.setOnClickListener(v -> Toast.makeText(context, comment, Toast.LENGTH_LONG).show());
        }else holder.image7.setVisibility(View.INVISIBLE);}catch (NullPointerException ignored){}


    }

    @Override
    public int getItemCount() {
         return arrayItems.size();
    }

  public static class adapterHolder extends RecyclerView.ViewHolder{
        TextView days;
        ImageView image7;


        public adapterHolder(@NonNull View itemView) {
            super(itemView);

            days= itemView.findViewById(R.id.days);
            image7= itemView.findViewById(R.id.image7);




        }


    }
    }



