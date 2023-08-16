package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.google.android.gms.common.internal.Objects;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class restaurant_signIn extends AppCompatActivity {
    TextView tv_signUp, tv_text;
    Button bt_signIn;
    EditText et_resName, et_password;
    DatabaseReference restR, rootR;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_in);
        getSupportActionBar().hide();
        tv_signUp = findViewById(R.id.restaurant_signIn_tv_signIn);
        bt_signIn = findViewById(R.id.restaurant_signIn_bt_signIn);
        et_password = findViewById(R.id.restaurant_signIn_et_password);
        et_resName = findViewById(R.id.restaurant_signIn_et_restaurantName);
        tv_text = findViewById(R.id.restaurant_signIn_tv_text);
        restR = FirebaseDatabase.getInstance().getReference().child("restaurant");
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        progressBar = findViewById(R.id.restaurant_signIn_pro);
        progressBar.setVisibility(View.GONE);

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_signIn.this, restaurant_signUp.class);
                startActivity(intent);
            }
        });

        bt_signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String resName = et_resName.getText().toString();
                String password = et_password.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if (!resName.equals("") && !password.equals("")) {
                    restR.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(resName)){
                                String getPassword = snapshot.child(resName).child("password").getValue(String.class);
                                if (getPassword.equals(password)){
                                    editor.putString("restaurantName", resName);
                                    editor.commit();
                                    Toast.makeText(restaurant_signIn.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(restaurant_signIn.this, mainRestaurantActivity.class);
                                    intent.putExtra("resName",resName);
                                    startActivity(intent);
                                    finish();
                                }else {
                                    tv_text.setText("Password is wrong");
                                    progressBar.setVisibility(View.GONE);
                                }
                            }else {
                                tv_text.setText("Restaurant name is not exist");
                                progressBar.setVisibility(View.GONE);
                            }
//                            for (DataSnapshot keyId : snapshot.getChildren()) {
//                                if (keyId.child("restaurant_name").getValue().equals(resName)) {
//                                    String getPassword = snapshot.child("password").getValue(String.class);
//                                    if (getPassword.equals(password)) {
//                                        editor.putString("restaurantName", resName);
//                                        editor.commit();
//                                        Toast.makeText(restaurant_signIn.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(restaurant_signIn.this, mainRestaurantActivity.class);
//                                        intent.putExtra("resName", resName);
//                                        startActivity(intent);
//                                        finish();
//                                    } else {
//                                        tv_text.setText("Password is wrong");
//                                    }
//                                }
//                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    tv_text.setText("Please enter all fields");
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
}