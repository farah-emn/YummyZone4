package com.example.yummyzone.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.user;
import com.example.yummyzone.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class eidtProfileFragment extends Fragment {
    EditText et_firstName;
    EditText et_lastName;
    EditText et_mobileNumber;
    EditText et_city;
    EditText et_street;
    EditText et_district;
    TextView tv_firstName;
    TextView tv_username;

    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    String username;
    String firstName;
    String lastName;
    String mobileNumber;
    String City;
    String street;
    String district;
    Button bt_save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eidt_profile, container, false);
        et_firstName = view.findViewById(R.id.editProfile_et_firstName);
        et_lastName = view.findViewById(R.id.editProfile_et_lastName);
        et_mobileNumber= view.findViewById(R.id.editProfile_et_mobileNmber);
        et_city= view.findViewById(R.id.editProfile_et_city);
        et_street= view.findViewById(R.id.editProfile_et_street);
        et_district= view.findViewById(R.id.editProfile_et_district);
        tv_firstName = view.findViewById(R.id.editprofile_tv_firstName);
        bt_save = view.findViewById(R.id.editProfile_bt_save);
        tv_username = view.findViewById(R.id.editProfile_tv_username);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");

        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId : snapshot.getChildren()){
                    if (keyId.child("email").getValue().equals(user.getEmail())){
                        username = keyId.child("username").getValue(String.class);
                        firstName = keyId.child("firstName").getValue(String.class);
                        lastName = keyId.child("lastName").getValue(String.class);
                        mobileNumber = keyId.child("mobileNumber").getValue(String.class);
                        City = keyId.child("city").getValue(String.class);
                        street = keyId.child("street").getValue(String.class);
                        district = keyId.child("district").getValue(String.class);
                    }
                }

                et_firstName.setText(firstName);
                et_lastName.setText(lastName);
                et_city.setText(City);
                et_street.setText(street);
                et_mobileNumber.setText(mobileNumber);
                et_district.setText(district);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_firstName =String.valueOf(et_firstName.getText()) ;
                String ed_lastName = et_lastName.getText().toString();
                String ed_mobileNumber = et_mobileNumber.getText().toString();
                String ed_city = et_city.getText().toString();
                String ed_street = et_street.getText().toString();
                String ed_district = et_district.getText().toString();

            }
        });


        return view;
    }
}