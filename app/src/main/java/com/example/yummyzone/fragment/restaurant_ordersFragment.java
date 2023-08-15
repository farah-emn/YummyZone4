package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.newOrderAdapter;
import com.example.yummyzone.adapter.orderTabAdaptar;
import com.example.yummyzone.adapter.shippedStateAdapter;
import com.example.yummyzone.classes.newOrder;
import com.example.yummyzone.classes.shippedState;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class restaurant_ordersFragment extends Fragment {

    RecyclerView rv_new, rv_shipped;
    String resName;
    SharedPreferences sharedPreferences;
    ArrayList<newOrder> newOrderList;
    ArrayList<shippedState> shippedStateList;
    newOrderAdapter newOrderAdapter;
    shippedStateAdapter shippedAdapter;
    String orderNumber, date, price, address, mobileNumber, reject, seeDetails;
    DatabaseReference resR, orderR, rootR;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_orders, container, false);
        rv_new = view.findViewById(R.id.restaurant_orders_rv_new);
        rv_shipped = view.findViewById(R.id.restaurant_orders_rv_shipped);
        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        resName = sharedPreferences.getString("restaurantName", "");
        rootR = FirebaseDatabase.getInstance().getReference();
        orderR = rootR.child("order");
        resR = rootR.child("restaurant");

        RecyclerView.LayoutManager lm1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_new.setHasFixedSize(true);
        rv_new.setLayoutManager(lm1);

        RecyclerView.LayoutManager lm2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_shipped.setHasFixedSize(true);
        rv_shipped.setLayoutManager(lm2);

        newOrderList = new ArrayList<>();
        newOrderAdapter = new newOrderAdapter(getContext(), newOrderList, resName);
        rv_new.setAdapter(newOrderAdapter);

        shippedStateList = new ArrayList<>();
        shippedAdapter = new shippedStateAdapter(getContext(), shippedStateList, resName);
        rv_shipped.setAdapter(shippedAdapter);

        orderR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("restaurantName").getValue().equals(resName)){
                        if(keyId.child("orderState").getValue().equals("new")){
                            mobileNumber = keyId.child("mobile").getValue(String.class);
                            date = keyId.child("date").getValue(String.class);
                            price = keyId.child("price").getValue(String.class);
                            orderNumber = keyId.child("orderNumber").getValue(String.class);
                            String street = keyId.child("street").getValue(String.class);
                            String city = keyId.child("city").getValue(String.class);
                            String district = keyId.child("district").getValue(String.class);
                            address = city+", "+ district+", "+street;
                        }
                    }
                    if (price != null){
                        newOrder n = new newOrder(orderNumber, date, price, address, mobileNumber);
                        newOrderList.add(n);
                    }
                }
                newOrderAdapter.notifyDataSetChanged();

                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("restaurantName").getValue().equals(resName)){
                        if(keyId.child("orderState").getValue().equals("shipped")){
                            mobileNumber = keyId.child("mobile").getValue(String.class);
                            date = keyId.child("date").getValue(String.class);
                            price = keyId.child("price").getValue(String.class);
                            orderNumber = keyId.child("orderNumber").getValue(String.class);
                            String street = keyId.child("street").getValue(String.class);
                            String city = keyId.child("city").getValue(String.class);
                            String district = keyId.child("district").getValue(String.class);
                            address = city+", "+ district+", "+street;
                            if (price != null){
                                shippedState s = new shippedState(orderNumber, date, price, address, mobileNumber);
                                shippedStateList.add(s);
                            }
                        }
                    }
//                    if (price != null){
//                        shippedState s = new shippedState(orderNumber, date, price, address, mobileNumber);
//                        shippedStateList.add(s);
//                    }
                }
                shippedAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

//        orderR.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                shippedStateList.clear();
//                for (DataSnapshot keyId : snapshot.getChildren()) {
//                    if (keyId.child("restaurantName").getValue().equals(resName)){
//                        if(keyId.child("orderState").getValue().equals("d")){
//                            mobileNumber = keyId.child("mobile").getValue(String.class);
//                            date = keyId.child("date").getValue(String.class);
//                            price = keyId.child("price").getValue(String.class);
//                            orderNumber = keyId.child("orderNumber").getValue(String.class);
//                            String street = keyId.child("street").getValue(String.class);
//                            String city = keyId.child("city").getValue(String.class);
//                            String district = keyId.child("district").getValue(String.class);
//                            address = city+", "+ district+", "+street;
//                        }
//                    }
//                    if (price != null){
//                        shippedState s = new shippedState(orderNumber, date, price, address, mobileNumber);
//                        shippedStateList.add(s);
//                    }
//                }
//                shippedAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });


        return view;
    }
}

//package com.example.yummyzone.fragment;
//
//        import android.content.Context;
//        import android.content.SharedPreferences;
//        import android.os.Bundle;
//
//        import androidx.fragment.app.Fragment;
//        import androidx.fragment.app.FragmentPagerAdapter;
//        import androidx.viewpager.widget.ViewPager;
//
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//
//        import com.example.yummyzone.R;
//        import com.example.yummyzone.adapter.orderTabAdaptar;
//        import com.google.android.material.tabs.TabLayout;
//
//
//public class restaurant_ordersFragment extends Fragment {
//
//    TabLayout tabLayout;
//    ViewPager viewPager;
//    String resName;
//    SharedPreferences sharedPreferences;
////    public restaurant_ordersFragment(String resName){
////        this.resName= resName;
////    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_restaurant_orders, container, false);
//        tabLayout = view.findViewById(R.id.restaurant_orders_tabs);
//        viewPager = view.findViewById(R.id.restaurant_orders_viewPager);
//        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
//        resName = sharedPreferences.getString("restaurantName", "");
//        tabLayout.setupWithViewPager(viewPager);
//        orderTabAdaptar tabA = new orderTabAdaptar(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//        tabA.addFragment(new newFragment(), "New");
//        tabA.addFragment(new shippedFragment(), "Shipped");
//
//
//        viewPager.setAdapter(tabA);
//
//        return view;
//    }
//}