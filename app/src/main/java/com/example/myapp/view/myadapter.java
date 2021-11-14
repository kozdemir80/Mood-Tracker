package com.example.myapp.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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


    @SuppressLint({"SetTextI18n", "NonConstantResourceId", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull adapterHolder holder, int position) {
        Moods myMood= arrayItems.get(position);




        try {

        TextView textView=holder.days;
        ImageView imageView= holder.image7;
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
            LinearLayout.LayoutParams leftLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams rightLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            float weight ;
            switch (myMood.getColors()){
                case-2212784:
                    weight = 0.2f;
                    break;

                case -6579301:
                    weight = 0.4f;
                    break;
                case -1522103591:
                    weight = 0.6f;
                    break;
                case -4658810:
                    weight = 0.8f;
                    break;
                case -398257:
                    weight = 1.0f;
                    break;

                default:
                    weight = 0.8f;
            }
            leftLayoutParams.weight = weight;
            rightLayoutParams.weight = 1.0f - weight;
            holder.leftFrameLayout.setLayoutParams(leftLayoutParams);
            holder.rightFrameLayout.setLayoutParams(rightLayoutParams);
            holder.leftFrameLayout.setBackgroundColor(myMood.getColors());
            











        }catch (NullPointerException ignored){
        }


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
      private FrameLayout leftFrameLayout;
      private FrameLayout rightFrameLayout;




        public  adapterHolder(@NonNull View itemView) {
            super(itemView);

            days= itemView.findViewById(R.id.days);
            image7= itemView.findViewById(R.id.image7);
            leftFrameLayout = itemView.findViewById(R.id.left_frame_layout);
            rightFrameLayout = itemView.findViewById(R.id.right_frame_layout);





        }

    }
    }



