package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.example.yummyzone.R;
import com.example.yummyzone.activites.editProfile;
import com.example.yummyzone.activites.signIn;
import com.example.yummyzone.activites.signUp;


public class profileFragment extends Fragment {

    Switch sw_dark;
    boolean nightMode;
    Button bt_edit;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sw_dark = view.findViewById(R.id.settings_switch_dark);
        bt_edit=view.findViewById(R.id.proile_bt_edit);


        sharedPreferences = getContext().getSharedPreferences("mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        if(nightMode){
            sw_dark.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new eidtProfileFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
                //Intent intent = new Intent(getContext(), editProfile.class);
               // startActivity(intent);
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
        return view;
        }}