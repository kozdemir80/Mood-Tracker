package com.example.myapp.controller;

import static com.example.myapp.controller.MainActivity.moodColorsArray;
import static com.example.myapp.controller.MainActivity.moodImagesArray;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.O)
public class history extends AppCompatActivity {
    private static SharedPreferences preferences;
    RecyclerView recyclerView;
    @SuppressLint("SimpleDateFormat")
    ZoneId z = ZoneId.of( "ECT" ) ;
    LocalDate today = LocalDate.now( z ) ;
    LocalDate myFormatedDate = today.minusDays(7) ;




    @SuppressLint({"ResourceType", "NewApi"})
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        recyclerView=findViewById(R.id.mview);
        myadapter adapter = new myadapter((ArrayList<Moods>) setList(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public  ArrayList<Moods> getList() {
        ArrayList<Moods> arrayItems = null;
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
    public static ArrayList<Moods> setList(){
        ArrayList<Moods> exampleList=new ArrayList<Moods>();
        String[] moodDays={"yesterday",
                "2 days ago",
                "3 days ago",
                "4 days ago",
                "5 days ago",
                "6 days ago",
                "7 days ago"};
        for (int i =0;i>moodDays.length; i++){
        Moods moods=new Moods();
        moods.setComment(moodDays[i]);
        moods.setColors(moodColorsArray[i]);
        exampleList.add(moods);
        }

        return exampleList;
    }

}