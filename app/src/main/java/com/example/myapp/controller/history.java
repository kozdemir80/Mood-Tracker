package com.example.myapp.controller;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.model.Moods;
import com.example.myapp.view.myadapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;public class history extends AppCompatActivity {

    private ListView mlistView;
    private TextView days;
    private SharedPreferences preferences;
    final Date date = new Date();
    @SuppressLint("SimpleDateFormat")
    final String myFormatedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);


    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        ImageView image7 = findViewById(R.id.image7);
        try {
            myadapter adapter = new myadapter(this, getList(), days, image7);
            mlistView = findViewById(R.id.mListView);
            mlistView.setAdapter(adapter);
        } catch (NullPointerException ignored) {
        }

        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Toast.makeText(getApplicationContext(), (CharSequence) getList(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public Moods getList() {
        List<Moods> arrayItems = null;
        try {
            String json = preferences.getString(myFormatedDate, null);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Moods>>() {}.getType();
            arrayItems = gson.fromJson(json, type);
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, Moods.getColors(), Collections.singletonList(Moods.getComment()));
            mlistView.setAdapter(arrayAdapter);
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "Mood retrieved", Toast.LENGTH_LONG).show();
        }
        return (Moods) arrayItems;


    }
}