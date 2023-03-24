package com.example.yummyzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class signIn extends AppCompatActivity {
    TextView signUp_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        signUp_tv = findViewById(R.id.signUp_tv_signIn);
        signUp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signIn.this, signUp.class);
                startActivity(intent);
            }
        });
    }
}