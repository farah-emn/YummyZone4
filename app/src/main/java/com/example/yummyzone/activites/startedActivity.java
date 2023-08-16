package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class startedActivity extends AppCompatActivity {
    Button bt_customer;
    TextView bt_restaurant;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String resName;
    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (!resName.equals("")){
            Intent intent1 = new Intent(startedActivity.this, mainRestaurantActivity.class);
            startActivity(intent1);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started);
        getSupportActionBar().hide();

        bt_customer = findViewById(R.id.started_bt_customer);
        bt_restaurant = findViewById(R.id.restaurant_started_bt_restaurant);
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        resName = sharedPreferences.getString("restaurantName", "");
        mAuth = FirebaseAuth.getInstance();

//        if (resName.equals("")){
//            Intent intent = new Intent(startedActivity.this, mainRestaurantActivity.class);
//            startActivity(intent);
//            finish();
//        }


        bt_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startedActivity.this, slide.class);
                startActivity(intent);
            }
        });

        bt_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(startedActivity.this, restaurant_signIn.class);
                startActivity(intent);
            }
        });

    }
}