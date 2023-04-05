package com.example.yummyzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class homeScreen extends AppCompatActivity {

    BottomNavigationView nav;
    RecyclerView tab_rv;
    RecyclerView restaurant_rv;
    TextView tab_name;
    ImageView tab_image;
    ImageView restaurant_image;
    TextView restaurant_name;
    TextView description;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getSupportActionBar().hide();

        nav = findViewById(R.id.homeScreenNav);
        tab_rv=findViewById(R.id.homeScreen_rv_tabs);
        restaurant_rv=findViewById(R.id.homeScreen_rv_restaurant);

        tab_name =findViewById(R.id.tab_name);
        tab_image=findViewById(R.id.tab_image);
        restaurant_image =findViewById(R.id.homeScreen_restaurant_image);
        description =findViewById(R.id.homeScreen_tv_description);
        restaurant_name =findViewById(R.id.homeScreen_tv_restaurantName);
        price =findViewById(R.id.homeScreen_tv_price);


        ArrayList<tab> tab_array = new ArrayList<>();
        tab_array.add(new tab("coffee", R.drawable.icon_favorite_gray));
        tab_array.add(new tab("ice cream", R.drawable.icon_favorite_gray));
        tab_array.add(new tab("ice cream", R.drawable.icon_person_gray));
        tab_array.add(new tab("ice cream", R.drawable.icon_favorite_gray));
        tab_array.add(new tab("ice cream", R.drawable.icon_favorite_gray));
        tab_array.add(new tab("ice cream", R.drawable.icon_favorite_gray));
        tabAdapter ad = new tabAdapter(tab_array);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        tab_rv.setHasFixedSize(true);
        tab_rv.setLayoutManager(lm);
        tab_rv.setAdapter(ad);

        ArrayList<restaurant> restaurant_array = new ArrayList<>();
        restaurant_array.add(new restaurant(R.drawable.dominos, "dominos","pizza", "10 SR" ));
        restaurant_array.add(new restaurant(R.drawable.baskin_robbins, "baskin robbins","ice cream", "19 SR" ));
        restaurant_array.add(new restaurant(R.drawable.herfy, "herfy","fast food", "12 SR" ));       restaurant_array.add(new restaurant(R.drawable.dominos, "dominos","pizza", "10 SR" ));
        restaurant_array.add(new restaurant(R.drawable.baskin_robbins, "baskin robbins","ice cream", "19 SR" ));
        restaurant_array.add(new restaurant(R.drawable.herfy, "herfy","fast food", "12 SR" ));       restaurant_array.add(new restaurant(R.drawable.dominos, "dominos","pizza", "10 SR" ));
        restaurant_array.add(new restaurant(R.drawable.baskin_robbins, "baskin robbins","ice cream", "19 SR" ));
        restaurant_array.add(new restaurant(R.drawable.herfy, "herfy","fast food", "12 SR" ));
        restaurantAdapter ad2 = new restaurantAdapter(restaurant_array);
        RecyclerView.LayoutManager lm2 = new GridLayoutManager(this,2);
        restaurant_rv.setLayoutManager(lm2);
        restaurant_rv.setAdapter(ad2);
        restaurant_rv.setHasFixedSize(true);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });


    }
}