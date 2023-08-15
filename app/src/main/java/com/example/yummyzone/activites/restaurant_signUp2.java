package com.example.yummyzone.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.restaurantAccount;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class restaurant_signUp2 extends AppCompatActivity {
    Button bt_signUp;
    DatabaseReference restR, rootR;
    FirebaseAuth mAuth;
    EditText et_mobile, et_time, et_fee;
    String mobile, time, fee, comNum, resName, password;
    TextView tv_text, tv_selected;
    String [] category = {"Beverages", "Burgers", "Coffee", "Desserts", "Sandwiches", "Pizza", "Sea Food"};
    ArrayList<Integer> list = new ArrayList<>();
    MaterialCardView selectList;
    boolean [] selected;
    StringBuilder stringBuilder;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up2);
        getSupportActionBar().hide();
        bt_signUp = findViewById(R.id.restaurant_signUp2_bt_signUp);
        et_mobile = findViewById(R.id.restaurant_signUp2_et_mobile);
        et_time = findViewById(R.id.restaurant_signUp2_et_deliveryTime);
        et_fee = findViewById(R.id.restaurant_signUp2_et_deliveryFee);
        tv_text = findViewById(R.id.restaurant_signUp2_tv_text);
        selectList = findViewById(R.id.restaurant_signUp2_selectCard);
        tv_selected = findViewById(R.id.restaurant_signUp2_tv_selected);
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        progressBar = findViewById(R.id.restaurant_signUp2_pro);
        progressBar.setVisibility(View.GONE);

        comNum = getIntent().getStringExtra("comNum");
        resName = getIntent().getStringExtra("resName");
        password = getIntent().getStringExtra("password");
        mAuth = FirebaseAuth.getInstance();
        restR = FirebaseDatabase.getInstance().getReference().child("restaurant");
        rootR = FirebaseDatabase.getInstance().getReference();


        selected = new boolean[category.length];
        tv_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(restaurant_signUp2.this);
                builder.setTitle("Select Categories");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(category, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        if (isChecked){
                            list.add(which);
                        }else {
                            list.remove(which);
                        }
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        stringBuilder = new StringBuilder();
                        for (int i=0; i<list.size(); i++){
                            stringBuilder.append(category[list.get(i)]);
                            if (i != list.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        tv_selected.setText(stringBuilder.toString());
                    }
                }).setNegativeButton("Cansel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i=0; i<selected.length; i++){
                            selected[i] = false;
                            list.clear();
                            tv_selected.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = et_mobile.getText().toString();
                fee = et_fee.getText().toString();
                time = et_time.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                restR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(resName).exists()) {
                           // progressBar.setVisibility(View.GONE);
                            tv_text.setText("The restaurant name is already exit");
                        } else {
                            if (!mobile.equals("") && !fee.equals("") && !time.equals("") && list.size()!=0) {
                                String categories = stringBuilder.toString();
                                restaurantAccount resAccount = new restaurantAccount(resName, password, comNum, fee, time, mobile, categories);
                                restR.child(resName).setValue(resAccount);
                                editor.putString("restaurantName", resName);
                                editor.commit();
                                Toast.makeText(restaurant_signUp2.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(restaurant_signUp2.this, mainRestaurantActivity.class);
                                startActivity(intent);
                                finish();


                            }else {
                                tv_text.setText("Please enter all fields");
                                progressBar.setVisibility(View.GONE);
                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

            }
        });


    }
}