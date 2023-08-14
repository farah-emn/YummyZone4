package com.example.yummyzone.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.order;
import com.example.yummyzone.classes.order2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class orderActivity extends AppCompatActivity {
    TextView tv_date;
    TextView tv_price;
    TextView tv_charge;
    TextView tv_total;
    Button bt_order;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR, cartR, orderR ;
    int orderNumber = 0;
    String username;
    Query itemsQuery ;
    order o;
    String mobileNumber;

    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().hide();

        String delivery_fee= getIntent().getStringExtra("delivery_fee");
        String total_price = getIntent().getStringExtra("total");
        String restaurantName = getIntent().getStringExtra("restaurant_name");
        String city = getIntent().getStringExtra("city");
        String street = getIntent().getStringExtra("street");
        String district = getIntent().getStringExtra("district");

        tv_date = findViewById(R.id.order_tv_date);
        tv_price = findViewById(R.id.order_tv_price);
        tv_charge=findViewById(R.id.order_tv_fet);
        tv_total=findViewById(R.id.order_tv_total);
        bt_order = findViewById(R.id.order_bt);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference("restaurant");
        userR = FirebaseDatabase.getInstance().getReference("user");
        cartR = FirebaseDatabase.getInstance().getReference("Cart");
        orderR = FirebaseDatabase.getInstance().getReference("order");
        database = FirebaseDatabase.getInstance();

        ArrayList<order> items = new ArrayList<order>();

        Long date=System.currentTimeMillis();
        SimpleDateFormat dateFormat =new SimpleDateFormat("dd / MMMM / yyyy - HH:mm", Locale.getDefault());
        String dateStr = dateFormat.format(date);
        Double  i = Double.parseDouble(total_price)+Double.parseDouble(delivery_fee);



        bt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot keyId : snapshot.getChildren()) {
                            if (keyId.child("email").getValue().equals(user.getEmail())) {
                                username = keyId.child("username").getValue().toString();
                                mobileNumber = keyId.child("mobileNumber").getValue().toString();
                                cartR.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for (DataSnapshot dataSnapshot:snapshot.getChildren()) {
                                            orderNumber++;
                                            String itemName = dataSnapshot.child("item_name").getValue(String.class);
                                            String count = dataSnapshot.child("qty").getValue(String.class);
                                            o = new order(itemName, count);
                                            items.add(o);
                                            orderR.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    long count = snapshot.getChildrenCount()+1;
                                                    order2 o2 = new order2(items, mobileNumber, restaurantName, username, dateStr, String.valueOf(i), city, street, district,String.valueOf(count) ,"new");
                                                    orderR.child(String.valueOf(count)).setValue(o2);
                                                }
                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }}

                        cartR.child(username).setValue(null);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Intent intent = new Intent(orderActivity.this, confirimationActivity.class);
                intent.putExtra("items",items);
                startActivity(intent);
            }
        });


        tv_date.setText(dateStr);
        tv_price.setText(total_price +" "+"SR");
        tv_charge.setText(delivery_fee+" "+"SR");
        tv_total.setText(Double.parseDouble(total_price)+Double.parseDouble(delivery_fee)+" "+"SR");
    }

}
