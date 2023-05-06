package com.example.yummyzone.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.cartItemAdapter;
import com.example.yummyzone.adapter.restaurantAdapter;
import com.example.yummyzone.adapter.tabAdapter;
import com.example.yummyzone.classes.cartItem;
import com.example.yummyzone.classes.restaurant;
import com.example.yummyzone.classes.tab;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class homeFragment extends Fragment {

    RecyclerView tab_rv;
    RecyclerView restaurant_rv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        tab_rv=view.findViewById(R.id.homeScreen_rv_tabs);
        restaurant_rv=view.findViewById(R.id.homeScreen_rv_restaurant);


        ArrayList<tab> tab_array = new ArrayList<>();
        tab_array.add(new tab("Fast food", R.drawable.icon_fastfood));
        tab_array.add(new tab("Sandwiches", R.drawable.icon_sandwiche));
        tab_array.add(new tab("Desserts", R.drawable.icon_dessert));
        tab_array.add(new tab("Beverages", R.drawable.icon_beverage));
        tab_array.add(new tab("Coffee", R.drawable.icon_coffee));
        tab_array.add(new tab("Pizza", R.drawable.icon_pizza));
        tab_array.add(new tab("Meat", R.drawable.icon_meat));
        tab_array.add(new tab("Noodles", R.drawable.icon_noodles));
        tabAdapter ad = new tabAdapter(tab_array);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL, false);
        tab_rv.setHasFixedSize(true);
        tab_rv.setLayoutManager(lm);
        tab_rv.setAdapter(ad);

        ArrayList<restaurant> restaurant_array = new ArrayList<>();
        restaurant_array.add(new restaurant(R.drawable.dominos, "Dominos","pizza", "10 SR" ));
        restaurant_array.add(new restaurant(R.drawable.baskin_robbins, "Baskin robbins","ice cream", "19 SR" ));
        restaurant_array.add(new restaurant(R.drawable.herfy, "Herfy","fast food", "12 SR" ));       restaurant_array.add(new restaurant(R.drawable.dominos, "dominos","pizza", "10 SR" ));
        restaurant_array.add(new restaurant(R.drawable.baskin_robbins, "Baskin robbins","ice cream", "19 SR" ));
        restaurant_array.add(new restaurant(R.drawable.herfy, "Herfy","fast food", "12 SR" ));       restaurant_array.add(new restaurant(R.drawable.dominos, "dominos","pizza", "10 SR" ));
        restaurant_array.add(new restaurant(R.drawable.baskin_robbins, "Baskin robbins","ice cream", "19 SR" ));
        restaurant_array.add(new restaurant(R.drawable.herfy, "Herfy","fast food", "12 SR" ));
        restaurantAdapter ad2 = new restaurantAdapter(restaurant_array);
        RecyclerView.LayoutManager lm2 = new GridLayoutManager(this.getContext(),2);
        restaurant_rv.setLayoutManager(lm2);
        restaurant_rv.setAdapter(ad2);
        restaurant_rv.setHasFixedSize(true);
        return view;
    }
}