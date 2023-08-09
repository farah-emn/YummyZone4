package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.shippedAdapter;
import com.example.yummyzone.classes.shipped;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class shippedActivity extends AppCompatActivity {
    RecyclerView shippedRec;
    shippedAdapter shA;
    ArrayList<shipped> shippedList;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, orderR, rootR, resR;
    String username;
    String img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipped);
        getSupportActionBar().hide();
        shippedRec= findViewById(R.id.shipped_rv);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        orderR = rootR.child("order");
        resR = rootR.child("restaurant");

        RecyclerView.LayoutManager lm1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        shippedRec.setHasFixedSize(true);
        shippedRec.setLayoutManager(lm1);

        shippedList = new ArrayList<>();
        shA = new shippedAdapter(this, shippedList);
        shippedRec.setAdapter(shA);

        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                        username = keyId.child("username").getValue(String.class);
                        orderR.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot keyId : snapshot.getChildren()) {
                                    if (keyId.child("username").getValue().equals(username)){
                                        String mobile = keyId.child("mobile").getValue(String.class);
                                        String date = keyId.child("date").getValue(String.class);
                                        String price = keyId.child("price").getValue(String.class);
                                        String restaurantName = keyId.child("restaurantName").getValue(String.class);
                                        resR.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                for (DataSnapshot keyId : snapshot.getChildren()) {
                                                    if (keyId.child("restaurant_name").getValue().equals(restaurantName)){
                                                        img = keyId.child("logo").getValue(String.class);
                                                    }
                                                }
                                            }
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                            }
                                        });
                                        shipped s = new shipped(mobile, restaurantName, date, price, img);
                                        shippedList.add(s);
                                    }
                                }
                                shA.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}