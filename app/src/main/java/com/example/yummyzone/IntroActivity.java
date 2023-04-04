package com.example.yummyzone;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class IntroActivity extends AppCompatActivity {
    private final int screen1_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getSupportActionBar().hide();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);

        if (firstStart){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(IntroActivity.this, StartedActivity.class);
                    startActivity(mainIntent);
                    finish(); }},screen1_DISPLAY_LENGTH);}

        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(IntroActivity.this, StartedActivity.class);
                    startActivity(mainIntent);
                    finish(); }},screen1_DISPLAY_LENGTH);
        }
        SharedPreferences prefs1 = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs1.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();

    }
}