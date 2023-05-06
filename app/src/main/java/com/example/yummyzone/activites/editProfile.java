package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import com.example.yummyzone.R;

public class editProfile extends AppCompatActivity {
    EditText et_firstName;
    EditText et_lastName;
    EditText et_mobileNumber;
    EditText et_street;
    EditText et_city;
    EditText et_district;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        getSupportActionBar().hide();
        et_firstName =findViewById(R.id.editProfile_et_firstName);
        et_lastName =findViewById(R.id.editProfile_et_lastName);

    }
}