package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yummyzone.adapter.Menu_Adapter;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Category_adapter;
import com.example.yummyzone.classes.Category_tab;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    RecyclerView RecyclerViewCategory,RecyclerViewFoodList;
    TextView item_name,item_price,pre_time,category_name;
    ImageView item_img,icon_favorite,tab_image;
    BottomNavigationView nav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        RecyclerViewCategory=findViewById(R.id.RecyclerViewCategory);
        RecyclerViewFoodList=findViewById(R.id.RecyclerViewFoodList);
        nav = findViewById(R.id.homeScreenNav);

        RecyclerView.LayoutManager lm1 =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerViewCategory.setHasFixedSize(true);
        RecyclerViewCategory.setLayoutManager(lm1);


        ArrayList<Menu_tab> tt =new ArrayList<>();
        RecyclerView.LayoutManager lm2 =new GridLayoutManager(this,2);
        RecyclerViewFoodList.setLayoutManager(lm2);


        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });

    }
}