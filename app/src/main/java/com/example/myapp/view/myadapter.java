package com.example.myapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.model.Moods;

import java.util.List;public class myadapter extends ArrayAdapter<String> {
    private final TextView days;
    private final ImageView image7;
    private final AppCompatActivity context;


    public myadapter(Context context, Moods resource, TextView days, ImageView image7) {
        super(context, R.layout.listview, (List<String>) resource);
        this.context= (AppCompatActivity) context;
        this.days = days;
        this.image7 = image7;
    }


    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null,true);
        return rowView;
    };
}
