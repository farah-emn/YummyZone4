package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class orderActivity extends AppCompatActivity {
    TextView tv_date;
    TextView tv_price;
    TextView tv_charge;
    TextView tv_total;
    Button bt_order;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR, cartR ;
    String delivery_fee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getSupportActionBar().hide();

        String total_price = getIntent().getStringExtra("total");
        String delivery_fee=getIntent().getStringExtra("delivery_fee");

        tv_date = findViewById(R.id.order_tv_date);
        tv_price = findViewById(R.id.order_tv_price);
        tv_charge=findViewById(R.id.order_tv_fet);
        tv_total=findViewById(R.id.order_tv_total);
        bt_order = findViewById(R.id.order_bt);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference("restaurant");
        userR = rootR.child("user");

//        rootR.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot keyId : snapshot.getChildren()){
//                    delivery_fee =(String) keyId.child(id).child("delivery_fee").getValue();
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });





        Long date=System.currentTimeMillis();
        SimpleDateFormat dateFormat =new SimpleDateFormat("dd / MMMM / yyyy - HH:mm", Locale.getDefault());
        String dateStr = dateFormat.format(date);
        tv_date.setText(dateStr.toString());
        tv_price.setText(total_price +" "+"SR");
        tv_charge.setText(delivery_fee+" "+"SR");
        tv_total.setText(Double.parseDouble(total_price)+Double.parseDouble(delivery_fee)+" "+"SR");


    }
}