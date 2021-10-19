package com.example.myapp.controller;

import static com.example.myapp.R.color.banana_yellow;
import static com.example.myapp.R.color.cornflower_blue_65;
import static com.example.myapp.R.color.faded_red;
import static com.example.myapp.R.color.light_sage;
import static com.example.myapp.R.color.warm_grey;

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
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.R;
import com.example.myapp.model.Moods;
import com.google.gson.Gson;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private ImageView mGreetingImageView;
    private GestureDetector mGestureDetector;
    private LinearLayout mLayout;
    private int index;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapp.R.layout.activity_main);

        //Initializing imageView
        mGreetingImageView = findViewById(R.id.smiley);
        //Initializing history button
        ImageButton mGreetingHistoryButton = findViewById(R.id.history);
        //Initializing adding comment button
        ImageButton mGreetingAddImageButton = findViewById(R.id.addcomment);
        //Initializing gesture detector
        mGestureDetector = new GestureDetector(this, new mGesture());
        //Initializing layout
        mLayout = findViewById(R.id.MainActivity);
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
            AlertDialog.Builder addComment = new AlertDialog.Builder(MainActivity.this);
            addComment.setTitle("Comment");
            addComment.setView(view);
            addComment.setNegativeButton("Cancel", (dialog, which) -> {

            });
            addComment.setPositiveButton("Confirm", (dialog, which) -> {
                {
                    for (int i = 1; i <= 7; i++) {
                        String myComment = editText.getText().toString();
                        int color = ((ColorDrawable) mLayout.getBackground()).getColor();
                        @SuppressLint({"NewApi", "LocalSuppress"})
                            LocalDate today = LocalDate.now();
                            LocalDate myFormatedDate = today.minusDays(1);

                        Log.d("yess be","ok!"+myFormatedDate);
                        Moods myMood = new Moods("", 0, 0, 0);
                        myMood.setComment(myComment);
                        myMood.setColors(color);

                        Gson gson = new Gson();

                        String myGson = gson.toJson(myMood);

                        SharedPreferences preferences = getSharedPreferences("myFile", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(String.valueOf(myFormatedDate), myGson);
                        editor.apply();

                        Toast.makeText(getApplicationContext(), "Comment successfully saved", Toast.LENGTH_SHORT).show();
                    }
                }


            });
            addComment.show();


        });
        // setting button to pass history activity
        mGreetingHistoryButton.setOnClickListener(v -> {
            Intent history = new Intent(MainActivity.this,history.class);
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

    public static String[] moodDays={"yesterday",
            "2 days ago",
            "3 days ago",
            "4 days ago",
            "5 days ago",
            "6 days ago",
            "7 days ago"

    };





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
        mLayout.setBackgroundResource(moodColorsArray[index]);
        return index;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
    }
