package com.example.myapp.controller;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.example.myapp.model.Moods;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BroadCastReciever extends BroadcastReceiver {
        private LinearLayout mLayout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        int color = ((ColorDrawable) mLayout.getBackground()).getColor();
        LocalDate myFormatedDate = LocalDate.now();
        @SuppressLint("WeekBasedYear")
        String sDate = myFormatedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Moods myMood = new Moods("", 0, 0);
        myMood.setColors(color);
        Gson gson = new Gson();
        String myGson = gson.toJson(myMood);
        SharedPreferences preferences = context.getSharedPreferences("myfile",Context.MODE_PRIVATE);
        Log.d(TAG, "onrecieve: "+preferences);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(sDate, myGson);
        editor.apply();
    }
}
