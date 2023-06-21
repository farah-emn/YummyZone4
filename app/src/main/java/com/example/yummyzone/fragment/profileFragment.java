package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.example.yummyzone.activites.editProfile;
import com.example.yummyzone.activites.signIn;
import com.example.yummyzone.activites.signUp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profileFragment extends Fragment {

    Switch sw_dark;
    boolean nightMode;
    Button bt_edit;
    Button bt_logout;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    TextView tv_name;
    String name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sw_dark = view.findViewById(R.id.settings_switch_dark);
        bt_edit=view.findViewById(R.id.proile_bt_edit);
        bt_logout = view.findViewById(R.id.profile_bt_logout);
        tv_name = view.findViewById(R.id.profile_tv_name);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");



        sharedPreferences = getContext().getSharedPreferences("mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        if(nightMode){
            sw_dark.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId : snapshot.getChildren()){
                    if (keyId.child("email").getValue().equals(user.getEmail())){
                        name = keyId.child("firstName").getValue(String.class);
                    }
                }
                tv_name.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new eidtProfileFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
            }
        });

        sw_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
             }});

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), signIn.class);
                startActivity(intent);
            }
        });
        return view;
        }}