package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.awt.font.TextAttribute;

public class restaurant_signUp extends AppCompatActivity {
    TextView tv_signIn;
    Button bt_continue;
    DatabaseReference userR;
    FirebaseAuth mAuth;
    TextView tv_text;
    EditText et_commNum, et_resName, et_password, et_confirmPassword;

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            Intent intent = new Intent(restaurant_signUp.this, mainRestaurantActivity.class);
//            startActivity(intent);
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        userR = FirebaseDatabase.getInstance().getReference().child("user");
        tv_signIn = findViewById(R.id.restaurant_signUp_tv_signIn);
        bt_continue = findViewById(R.id.restaurant_signUp_bt_continue);
        et_commNum = findViewById(R.id.restaurant_signUp_et_commercial);
        et_resName = findViewById(R.id.restaurant_signUp_et_restaurantName);
        et_password = findViewById(R.id.restaurant_signUp_et_password);
        tv_text = findViewById(R.id.restaurant_signUp_tv_text);
        et_confirmPassword = findViewById(R.id.restaurant_signUp_et_confirmPassword);

        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(restaurant_signUp.this, restaurant_signIn.class);
                startActivity(intent);
            }
        });

        bt_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comNum, resName, password, confirmPassword;
                comNum = et_commNum.getText().toString();
                resName = et_resName.getText().toString();
                password = et_password.getText().toString();
                confirmPassword= et_confirmPassword.getText().toString();

                if (!comNum.equals("") && !resName.equals("") && !password.equals("") && !confirmPassword.equals("")){
                    if (password.equals(confirmPassword)) {
                        Intent intent = new Intent(restaurant_signUp.this, restaurant_signUp2.class);
                        intent.putExtra("comNum", comNum);
                        intent.putExtra("resName", resName);
                        intent.putExtra("password", password);
                        startActivity(intent);
                    } else {
                        tv_text.setText("password does not match");
                    }
                }else {
                    tv_text.setText("Please enter all fields");
                }


            }
        });
    }
}