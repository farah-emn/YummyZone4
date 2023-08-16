package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;

public class confirimationActivity extends AppCompatActivity {
    FirebaseAuth Auth;
    DatabaseReference orderR, userR;
    FirebaseUser user;
    TextView tv_orderNumber;
    String order;
    Button confirm_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirimation);
        getSupportActionBar().hide();
        orderR = FirebaseDatabase.getInstance().getReference().child("order");
        userR = FirebaseDatabase.getInstance().getReference().child("user");
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        confirm_button=findViewById(R.id.confirm_button1);
        tv_orderNumber = findViewById(R.id.confirmation_tv_orderNumber);

        userR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                        String username = keyId.child("username").getValue(String.class);

                        orderR.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                tv_orderNumber.setText(String.valueOf( snapshot.getChildrenCount()));





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
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(confirimationActivity.this,MainActivity.class);
                startActivity(i);


            }
        });
    }
}