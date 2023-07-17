package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.yummyzone.R;
import com.example.yummyzone.fragment.cartFragment;
import com.example.yummyzone.fragment.favoriteFragment;
import com.example.yummyzone.fragment.homeFragment;
import com.example.yummyzone.fragment.profileFragment;
import com.example.yummyzone.fragment.searchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

//import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
//import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView nav;
    String post_key1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        nav = findViewById(R.id.main_nav);
        String postkey = "";
        String name = "";
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new homeFragment(post_key1)).commit();
        nav.setSelectedItemId(R.id.home);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new homeFragment(post_key1);

                        break;

                    case R.id.search:
                        fragment = new searchFragment();

                        break;

                    case R.id.favorite:

                        fragment = new favoriteFragment();
                        break;

                    case R.id.cart:
                        fragment = new cartFragment(name);
                        break;

                    case R.id.settings:
                        fragment = new profileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();

                return true;
            }
        });

    }
}