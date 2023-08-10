package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yummyzone.R;

public class restaurant_signUp2 extends AppCompatActivity {
    Button bt_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up2);
        getSupportActionBar().hide();
        bt_signUp = findViewById(R.id.restaurant_signUp2_bt_signUp);

        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_signUp2.this, mainRestaurantActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}