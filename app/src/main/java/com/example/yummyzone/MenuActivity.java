package com.example.yummyzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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
        item_name=findViewById(R.id.item_name);
        item_price=findViewById(R.id.item_price);
        pre_time=findViewById(R.id.pre_time);
        item_img=findViewById(R.id.item_img);
        icon_favorite=findViewById(R.id.icon_favorite);
        category_name = findViewById(R.id.tab_name);
        tab_image= findViewById(R.id.tab_image);
        nav = findViewById(R.id.homeScreenNav);

        ArrayList<Category_tab> tab_array_category =new ArrayList<>();
        tab_array_category.add(new Category_tab("All",R.drawable.icon_favorite_gray));
        tab_array_category.add(new Category_tab("pizza",R.drawable.icon_favorite_gray));
        tab_array_category.add(new Category_tab("burger",R.drawable.icon_favorite_gray));
        tab_array_category.add(new Category_tab("juice",R.drawable.icon_favorite_gray));
        tab_array_category.add(new Category_tab("pizza",R.drawable.icon_favorite_gray));
        Category_Adapter adapter_cat =new Category_Adapter(tab_array_category);
        RecyclerView.LayoutManager lm1 =new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerViewCategory.setHasFixedSize(true);
        RecyclerViewCategory.setLayoutManager(lm1);
        RecyclerViewCategory.setAdapter(adapter_cat);

        ArrayList<Menu_tab> tt =new ArrayList<>();
        tt.add(new Menu_tab( R.drawable.foodimage,R.drawable.icon_favorite_gray,"big testy","16 SAR","16 min"));
        tt.add(new Menu_tab( R.drawable.drink,R.drawable.icon_favorite_gray,"coca cola","16 SAR","16 min"));
        tt.add(new Menu_tab( R.drawable.drink,R.drawable.icon_favorite_gray,"coca cola","16 SAR","16 min"));
        tt.add(new Menu_tab( R.drawable.foodimage,R.drawable.icon_favorite_gray,"big testy","14 SAR","16 min"));
        tt.add(new Menu_tab( R.drawable.foodimage,R.drawable.icon_favorite_gray,"big testy","16 SAR","16 min"));
        tt.add(new Menu_tab( R.drawable.drink,R.drawable.icon_favorite_gray,"coca cola","16 SAR","16 min"));
        tt.add(new Menu_tab( R.drawable.drink,R.drawable.icon_favorite_gray,"coca cola","20 SAR","16 min"));
        tt.add(new Menu_tab( R.drawable.foodimage,R.drawable.icon_favorite_gray,"big testy","20 SAR","16 min"));
        Menu_Adapter add = new Menu_Adapter(tt);
        RecyclerView.LayoutManager lm2 =new GridLayoutManager(this,2);
        RecyclerViewFoodList.setLayoutManager(lm2);
        RecyclerViewFoodList.setAdapter(add);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return true;
            }
        });

    }
}