package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yummyzone.R;

import java.awt.font.TextAttribute;

public class restaurant_signUp extends AppCompatActivity {
    TextView tv_signIn;
    Button bt_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up);
        getSupportActionBar().hide();
        tv_signIn = findViewById(R.id.restaurant_signUp_tv_signIn);
        bt_continue = findViewById(R.id.restaurant_signUp_bt_continue);

        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_signUp.this, restaurant_signIn.class);
                startActivity(intent);
            }
        });

        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_signUp.this, restaurant_signUp2.class);
                startActivity(intent);
            }
        });
    }
}