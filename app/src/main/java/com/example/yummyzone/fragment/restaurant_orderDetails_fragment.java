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
import android.widget.TextView;

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
    String resName, orderNumber2;
    RecyclerView recyclerView;
    orderDetailsAdapter orderItemAdapter;
    ArrayList<orderItem> orderItemsList;
    TextView tv_orderNumber, tv_date, tv_price, tv_address, tv_mobileNumber, tv_reject, tv_ready;

    public restaurant_orderDetails_fragment(String orderNumber, String date, String price, String address, String mobileNumber){
        this.orderNumber = orderNumber;
        this.date = date;
        this.price = price;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_order_details, container, false);
        recyclerView = view.findViewById(R.id.restaurant_orderDetailsCard_rv);
        tv_price = view.findViewById(R.id.restaurant_orderDetailsCart_tv_orderPrice);
        tv_address = view.findViewById(R.id.restaurant_orderDetailsCart_tv_orderAddress);
        tv_date = view.findViewById(R.id.restaurant_orderDetailsCart_tv_orderDate);
        tv_orderNumber = view.findViewById(R.id.restaurant_orderDetailsCart_tv_orderNumber);
        tv_mobileNumber = view.findViewById(R.id.restaurant_orderDetailsCart_tv_mobile);
        tv_reject = view.findViewById(R.id.restaurant_orderDetailsCart_tv_reject);
        tv_ready = view.findViewById(R.id.restaurant_orderDetailsCart_tv_ready);
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

        tv_mobileNumber.setText(mobileNumber);
        tv_price.setText(price);
        tv_date.setText(date);
        tv_orderNumber.setText(orderNumber);
        tv_address.setText(address);

        tv_ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderR.child(orderNumber).child("orderState").setValue("shipped");
                restaurant_ordersFragment orderFragment = new restaurant_ordersFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, orderFragment).commit();
            }
        });

        tv_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderR.child(orderNumber).child("orderState").setValue("reject");
                restaurant_ordersFragment orderFragment = new restaurant_ordersFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, orderFragment).commit();
            }
        });


        orderR.child(orderNumber).child("items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId : snapshot.getChildren()){
                    orderItem o =new orderItem(keyId.child("itemName").getValue().toString(),keyId.child("itemNumber").getValue().toString());
                    orderItemsList.add(o);
                }
                orderItemAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;


    }
}