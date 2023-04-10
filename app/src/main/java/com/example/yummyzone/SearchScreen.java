package com.example.yummyzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SearchScreen extends AppCompatActivity {
ImageView tab_image_icecream,tab_image_FastFood,tab_image_Coffee,tab_image_Pizza,tab_image_desserts;
TextView tab_name_icecream,tab_name_FastFood,tab_name_Coffee,tab_name_Pizza,tab_name_desserts;
BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_screen);
        getSupportActionBar().hide();
        tab_image_icecream=findViewById(R.id.tab_image_icecream);
        tab_image_FastFood=findViewById(R.id.tab_image_FastFood);
        tab_image_Coffee=findViewById(R.id.tab_image_Coffee);
        tab_image_Pizza=findViewById(R.id.tab_image_Pizza);
        tab_image_desserts=findViewById(R.id.tab_image_desserts);

        tab_name_desserts=findViewById(R.id.tab_name_desserts);
        tab_name_Coffee=findViewById(R.id.tab_name_coffee);
        tab_name_Pizza=findViewById(R.id.tab_name_Pizza);
        tab_name_icecream=findViewById(R.id.tab_name_icecream);
        tab_name_FastFood=findViewById(R.id.tab_name_FastFood);

        nav = findViewById(R.id.homeScreenNav);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });


    }
}