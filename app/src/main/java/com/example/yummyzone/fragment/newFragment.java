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
import com.example.yummyzone.classes.newOrder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class newFragment extends Fragment {
    RecyclerView rv;
    newOrderAdapter newOrderAdapter;
    ArrayList<newOrder> newOrderList;
    DatabaseReference resR, orderR, rootR;
    String resName , resName2;
    String orderNumber, date, price, address, mobileNumber, reject, seeDetails;
    SharedPreferences sharedPreferences;

//    public newFragment(String resName){
//        this.resName = resName;
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        rv = view.findViewById(R.id.restaurant_fragment_new_rv);
        rootR = FirebaseDatabase.getInstance().getReference();
        orderR = rootR.child("order");
        resR = rootR.child("restaurant");
        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        resName2 = sharedPreferences.getString("restaurantName", "");


        RecyclerView.LayoutManager lm1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm1);

        newOrderList = new ArrayList<>();
        newOrderAdapter = new newOrderAdapter(getContext(), newOrderList, resName2);
        rv.setAdapter(newOrderAdapter);


        orderR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("restaurantName").getValue().equals(resName2)){
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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}