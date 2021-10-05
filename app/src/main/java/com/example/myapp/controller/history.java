package com.example.myapp.controller;

import static com.example.myapp.R.color.banana_yellow;
import static com.example.myapp.R.color.cornflower_blue_65;
import static com.example.myapp.R.color.faded_red;
import static com.example.myapp.R.color.light_sage;
import static com.example.myapp.R.color.warm_grey;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

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
     SharedPreferences preferences;
    RecyclerView recyclerView;
    @SuppressLint({"SimpleDateFormat", "ResourceType", "NewApi"})


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        recyclerView=(RecyclerView) findViewById(R.id.mview);
        myadapter adapter = new myadapter((ArrayList<Moods>) getList(),this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



    }


    public ArrayList<Moods> getList() {
        ArrayList<Moods> arrayItems = new ArrayList<>();

        try {
            ZoneId z = ZoneId.of("ECT");
            LocalDate today = LocalDate.now(z);
            for (int i = 1; i <= 7; i++) {
                LocalDate myFormatedDate = today.minusDays(i);
                String myGson = preferences.getString(String.valueOf(myFormatedDate), null);
                Gson gson = new Gson();
                Type type = new TypeToken<List<Moods>>() {}.getType();
                arrayItems = gson.fromJson(myGson, type);}
        } catch(NullPointerException ignored){}
        return arrayItems;
    }


    public static ArrayList<Moods> setList(){
        ArrayList<Moods> exampleList= new ArrayList<>();
        String[] Days={"yesterday",
                "2 days ago",
                "3 days ago",
                "4 days ago",
                "5 days ago",
                "6 days ago",
                "7 days ago"};
         int[] moodColor = {light_sage,
                faded_red,
                warm_grey,
                cornflower_blue_65,
                 light_sage,
                 faded_red,
                banana_yellow};
        int image []={R.drawable.comment,
                R.drawable.comment,
                R.drawable.comment,
                R.drawable.comment,
                R.drawable.comment,
                R.drawable.comment,
                R.drawable.comment,

        };
        for (int i = 0; i < Days.length; i++){
        Moods moods=new Moods("good mood",0,0);
        moods.setComment(Days[i]);
        moods.setColors(moodColor[i]);
        moods.setImage(image[i]);
        exampleList.add(moods);
        }

        return exampleList;
    }

}