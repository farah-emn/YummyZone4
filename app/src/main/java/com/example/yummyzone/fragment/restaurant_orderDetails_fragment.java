package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.newOrderAdapter;
import com.example.yummyzone.adapter.orderDetailsAdapter;
import com.example.yummyzone.classes.newOrder;
import com.example.yummyzone.classes.orderItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class restaurant_orderDetails_fragment extends Fragment {
    DatabaseReference resR, orderR, rootR;
    String orderNumber, date, price, address, mobileNumber;
    SharedPreferences sharedPreferences;
    String resName;
    RecyclerView recyclerView;
    orderDetailsAdapter orderItemAdapter;
    ArrayList<orderItem> orderItemsList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_order_details, container, false);
        recyclerView = view.findViewById(R.id.restaurant_orderDetailsCard_rv);
        rootR = FirebaseDatabase.getInstance().getReference();
        orderR = rootR.child("order");
        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        resName = sharedPreferences.getString("restaurantName", "");

        RecyclerView.LayoutManager lm1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm1);

        orderItemsList = new ArrayList<>();
        orderItemAdapter = new orderDetailsAdapter(getContext(), orderItemsList, resName);
        recyclerView.setAdapter(orderItemAdapter);

        orderR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("restaurantName").getValue().equals(resName)){
                            mobileNumber = keyId.child("mobile").getValue(String.class);
                            date = keyId.child("date").getValue(String.class);
                            price = keyId.child("price").getValue(String.class);
                            orderNumber = keyId.child("orderNumber").getValue(String.class);
                            String street = keyId.child("street").getValue(String.class);
                            String city = keyId.child("city").getValue(String.class);
                            String district = keyId.child("district").getValue(String.class);
                            address = city+", "+ district+", "+street;
                            for (DataSnapshot keyId2 : snapshot.getChildren()){}
                            //keyId2.child("items").
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return view;
    }
}