package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.yummyzone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class IntroActivity extends AppCompatActivity {
    private final int screen1_DISPLAY_LENGTH = 3000;
    String resName;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

  //  @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        if (!resName.equals("")){
//            Intent intent1 = new Intent(IntroActivity.this, mainRestaurantActivity.class);
//            startActivity(intent1);
//            finish();
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        resName = sharedPreferences.getString("restaurantName", "");
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart",true);
        if (firstStart){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(IntroActivity.this, startedActivity.class);
                    startActivity(mainIntent);
                    finish(); }},screen1_DISPLAY_LENGTH);}
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(IntroActivity.this, startedActivity.class);
                    startActivity(mainIntent);
                    finish(); }},screen1_DISPLAY_LENGTH);
        }
        SharedPreferences prefs1 = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs1.edit();
        editor.putBoolean("firstStart",false);
        editor.apply();

    }
}