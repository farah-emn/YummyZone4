package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

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

public class processingActivity extends AppCompatActivity {
    RecyclerView rv;
    shippedAdapter shA;
    ArrayList<shipped> shippedList;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, orderR, rootR, resR;
    String username;
    ImageView imageViewback;
    public static String img = "";
    ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        getSupportActionBar().hide();
        rv = findViewById(R.id.processing_rv);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        orderR = rootR.child("order");
        //imageViewback = findViewById(R.id.profile_image_back);
        resR = rootR.child("restaurant");


        RecyclerView.LayoutManager lm1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm1);

        shippedList = new ArrayList<>();
        shA = new shippedAdapter(this, shippedList);
        rv.setAdapter(shA);
        p = findViewById(R.id.processing_pro);

        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                        username = keyId.child("username").getValue(String.class);
                        orderR.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                p.setVisibility(View.GONE);

                                shippedList.clear();
                                for (DataSnapshot keyId : snapshot.getChildren()) {
                                    if (keyId.child("username").getValue().equals(username)) {
                                        p.setVisibility(View.GONE);
                                        if (keyId.getChildrenCount() > 0) {

                                            p.setVisibility(View.INVISIBLE);
                                        }
                                        if (keyId.child("orderState").getValue().equals("shipped")) {
                                            String mobile = keyId.child("mobile").getValue(String.class);
                                            String date = keyId.child("date").getValue(String.class);
                                            String price = keyId.child("price").getValue(String.class);
                                            String city = keyId.child("city").getValue(String.class);
                                            String district = keyId.child("district").getValue(String.class);
                                            String street = keyId.child("street").getValue(String.class);
                                            String restaurantName = keyId.child("restaurantName").getValue(String.class);
                                            String state = keyId.child("orderState").getValue().toString();
                                            resR.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    for (DataSnapshot keyId : snapshot.getChildren()) {
                                                        if (keyId.child("restaurant_name").getValue().equals(restaurantName)) {
                                                            img = String.valueOf(keyId.child("logo").getValue());
                                                            cf();
                                                        }
                                                    }
                                                }

                                                private void cf() {
                                                    shA.notifyDataSetChanged();

                                                    shipped s = new shipped(mobile, restaurantName, date, price, img, city, district, street, state);
                                                    shippedList.add(s);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {
                                                }
                                            });
                                        }
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
        p.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.dark_orange), PorterDuff.Mode.SRC_IN);
    }

}