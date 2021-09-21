package com.example.myapp.controller;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.model.Moods;
import com.example.myapp.view.myadapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
class xhistory extends AppCompatActivity {
    private TextView days;
    private SharedPreferences preferences;
    RecyclerView recyclerView;
    @SuppressLint("SimpleDateFormat")
    ZoneId z = ZoneId.of( "Europe" ) ;
    LocalDate today = LocalDate.now( z ) ;
    LocalDate myFormatedDate = today.minusDays( 90 ) ;



    @SuppressLint({"ResourceType", "NewApi"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        ImageView image7 = findViewById(R.id.image7);
        recyclerView=findViewById(R.id.mview);
        myadapter adapter = new myadapter(this.getList());
        recyclerView.setAdapter(adapter);
    }

    public  List<Moods> getList() {
        List<Moods> arrayItems = null;
        try {
            String json = preferences.getString(String.valueOf(myFormatedDate), null);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Moods>>() {}.getType();
            arrayItems = gson.fromJson(json, type);
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "Mood retrieved", Toast.LENGTH_LONG).show();
        }
        return arrayItems;

    }

}