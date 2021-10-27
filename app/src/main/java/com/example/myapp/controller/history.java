package com.example.myapp.controller;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Moods;
import com.example.myapp.view.myadapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


@RequiresApi(api = Build.VERSION_CODES.O)
public class history extends AppCompatActivity {
    RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    static TextView days;
    ImageView image7;

    @SuppressLint({"ResourceType", "NewApi"})


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        days=findViewById(R.id.days);
        image7=findViewById(R.id.image7);
        recyclerView= findViewById(R.id.mview);




        myadapter adapter = new myadapter(getList(),this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


    }




    @SuppressLint("WeekBasedYear")
    public ArrayList<Moods> getList() {
        ArrayList<Moods> arrayItems = new ArrayList<>();

        try {
            String sDate;
            SharedPreferences preferences = getSharedPreferences("myFile",MODE_PRIVATE);
            for (int i = 0; i <= 7; i++) {
                LocalDate today = LocalDate.now();
                LocalDate yesterday = today.minusDays(i);
                sDate = yesterday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String myGson = preferences.getString(String.valueOf(sDate), "");
                Gson gson = new Gson();
                Type type = new TypeToken<Moods>(){}.getType();
                Moods items = gson.fromJson(myGson, type);
                arrayItems.add(items);

            }


        } catch(NullPointerException ignored){}
        return arrayItems;
    }


}