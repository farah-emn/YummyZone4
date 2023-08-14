package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.yummyzone.R;
import com.example.yummyzone.fragment.cartFragment;
import com.example.yummyzone.fragment.favoriteFragment;
import com.example.yummyzone.fragment.homeFragment;
import com.example.yummyzone.fragment.profileFragment;
import com.example.yummyzone.fragment.restaurant_AddFragment;
import com.example.yummyzone.fragment.restaurant_menuFragment;
import com.example.yummyzone.fragment.restaurant_ordersFragment;
import com.example.yummyzone.fragment.restaurant_profileFragment;
import com.example.yummyzone.fragment.restaurant_searchFragment;
import com.example.yummyzone.fragment.searchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class mainRestaurantActivity extends AppCompatActivity {

    BottomNavigationView nav;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_restaurant);
        getSupportActionBar().hide();
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String res = sharedPreferences.getString("restaurantName", "");


        nav = findViewById(R.id.restaurant_main_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, new restaurant_menuFragment()).commit();
        nav.setSelectedItemId(R.id.home);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new restaurant_menuFragment();

                        break;

                    case R.id.search:
                        fragment = new restaurant_searchFragment();

                        break;

                    case R.id.orders:

                        fragment = new restaurant_ordersFragment();
                        break;

                    case R.id.add:
                        fragment = new restaurant_AddFragment();
                        break;

                    case R.id.settings:
                        fragment = new restaurant_profileFragment(res);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, fragment).commit();

                return true;
            }
        });

    }
}