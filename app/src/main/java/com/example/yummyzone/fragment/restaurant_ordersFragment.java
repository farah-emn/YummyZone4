package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.orderTabAdaptar;
import com.google.android.material.tabs.TabLayout;


public class restaurant_ordersFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    String resName;
    SharedPreferences sharedPreferences;
//    public restaurant_ordersFragment(String resName){
//        this.resName= resName;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_orders, container, false);
        tabLayout = view.findViewById(R.id.restaurant_orders_tabs);
        viewPager = view.findViewById(R.id.restaurant_orders_viewPager);
        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        resName = sharedPreferences.getString("restaurantName", "");
        tabLayout.setupWithViewPager(viewPager);
        orderTabAdaptar tabA = new orderTabAdaptar(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabA.addFragment(new newFragment(), "New");
        tabA.addFragment(new shippedFragment(), "Shipped");


        viewPager.setAdapter(tabA);

        return view;
    }
}