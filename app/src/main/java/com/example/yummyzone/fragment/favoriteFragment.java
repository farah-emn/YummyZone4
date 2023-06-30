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
import com.example.yummyzone.adapter.Menu_Adapter;
import com.example.yummyzone.adapter.cartItemAdapter;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.classes.cartItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class favoriteFragment extends Fragment {

    RecyclerView rv;

    public favoriteFragment(String postkey) {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favorite, container, false);

        rv = view.findViewById(R.id.favorite_rv);

        ArrayList<Menu_tab> tt =new ArrayList<>();
        RecyclerView.LayoutManager lm2 =new GridLayoutManager(this.getContext(),2);
        rv.setLayoutManager(lm2);
        return view;
    }
}