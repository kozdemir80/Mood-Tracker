package com.example.myapp.controller;

import static com.example.myapp.R.color.banana_yellow;
import static com.example.myapp.R.color.cornflower_blue_65;
import static com.example.myapp.R.color.faded_red;
import static com.example.myapp.R.color.light_sage;
import static com.example.myapp.R.color.warm_grey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myapp.R;
import com.example.myapp.model.Moods;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageButton mGreetingAddImageButton;
    private ImageButton mGreetingHistoryButton;
    private ImageView mGreetingImageView;
    private GestureDetector mgestureDetector;
    private LinearLayout mlayout;
    private int index;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing imageView
        mGreetingImageView = findViewById(R.id.smiley);
        //Initializing history button
        mGreetingHistoryButton = findViewById(R.id.history);
        //Initializing adding comment button
        mGreetingAddImageButton = findViewById(R.id.addcomment);
        //Initializing gesture detector
        mgestureDetector = new GestureDetector(this, new mGesture());
        //Initializing layout
        mlayout = findViewById(R.id.MainActivity);
        //Initializing index
        changeUi(index);
        scheduleAlarm();



        // setting comment button
        mGreetingAddImageButton.setOnClickListener(v -> {
            // inflating alertdialog layout
            LayoutInflater inflater = getLayoutInflater();
            View view=inflater.inflate(R.layout.alertdialog,null);
            EditText editText= view.findViewById(R.id.comment_here);
            // setting positive and negative button for adding comment section
            AlertDialog.Builder addcomment = new AlertDialog.Builder(MainActivity.this);
            addcomment.setTitle("Comment");
            addcomment.setView(view);
            addcomment.setNegativeButton("Cancel", (dialog, which) -> {

            });
            addcomment.setPositiveButton("Confirm", (dialog, which) -> {
                {
                    String myComment = editText.getText().toString();
                    int color = ((ColorDrawable) mlayout.getBackground()).getColor();
                    final Date date = new Date();
                    @SuppressLint("SimpleDateFormat") final String myFormatedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);

                    Moods mymood = new Moods();
                    Moods.setComment(myComment);
                    mymood.setColors(color);

                    Gson gson = new Gson();

                    String myGson = gson.toJson(mymood);

                    SharedPreferences preferences = getSharedPreferences("myFile", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(myFormatedDate, myGson);
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "Comment successfully saved", Toast.LENGTH_SHORT).show();
                }
            });
            addcomment.show();


        });
        // setting button to pass history activity
        mGreetingHistoryButton.setOnClickListener(v -> {
            Intent history = new Intent(MainActivity.this, history.class);
            startActivity(history);
        });



    }






    @RequiresApi(api = Build.VERSION_CODES.N)
    public void scheduleAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 ,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            );
        }

    }








    public static int[] moodImagesArray = {R.drawable.happy,
            R.drawable.sad,
            R.drawable.disappointed,
            R.drawable.normal,
            R.drawable.happy,
            R.drawable.superhappy};



    public static int[] moodColorsArray = {light_sage,
            faded_red,
            warm_grey,
            cornflower_blue_65,
            light_sage,
            banana_yellow};





    private class mGesture implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if (e1.getY() - e2.getY() > 30) {

                if (index > 0) {

                    index--;

                    changeUi(index);


                }

            }

            if (e2.getY() - e1.getY() > 30) {

                if (index < 5) {

                    index++;

                    changeUi(index);





                }

            }

            return true;
        }

    }

    private int changeUi(int index) {
        mGreetingImageView.setImageResource(moodImagesArray[index]);
        mlayout.setBackgroundResource(moodColorsArray[index]);
        return index;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mgestureDetector.onTouchEvent(event);
    }
    }
