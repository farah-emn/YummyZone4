package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yummyzone.R;

public class restaurant_signIn extends AppCompatActivity {
    TextView tv_signUp;
    Button bt_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_in);
        getSupportActionBar().hide();
        tv_signUp = findViewById(R.id.restaurant_signIn_tv_signIn);
        bt_signIn = findViewById(R.id.restaurant_signIn_bt_signIn);

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_signIn.this, restaurant_signUp.class);
                startActivity(intent);
            }
        });

        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_signIn.this, mainRestaurantActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}