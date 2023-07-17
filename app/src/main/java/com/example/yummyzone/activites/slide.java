package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.slideAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class slide extends AppCompatActivity {

    public static ViewPager viewPager;
    slideAdapter adapter;
    ConstraintLayout cl_signIn;
    ConstraintLayout cl_signUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        getSupportActionBar().hide();
        cl_signIn= findViewById(R.id.slide_signIn);
        cl_signUp= findViewById(R.id.slide_signUp);

        cl_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(slide.this, signIn.class);
                startActivity(intent);

            }
        });

        cl_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(slide.this, signUp.class);
                startActivity(intent);

            }
        });

        viewPager = findViewById(R.id.viewPager);
        adapter = new slideAdapter(this);
        viewPager.setAdapter(adapter);


    }}