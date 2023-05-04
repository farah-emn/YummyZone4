package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yummyzone.R;

public class signIn extends AppCompatActivity {
    TextView signUp_tv;
    Button bt_skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        signUp_tv = findViewById(R.id.signUp_tv_signIn);
        bt_skip = findViewById(R.id.signin_bt_skip);

        signUp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signIn.this, signUp.class);
                startActivity(intent);
            }
        });
        bt_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signIn.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}