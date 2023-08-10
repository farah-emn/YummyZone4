package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.yummyzone.R;

public class restaurant_profileFragment extends Fragment {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Switch sw_dark;
    boolean nightMode;
    ImageView img_edit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_profile, container, false);
        sw_dark = view.findViewById(R.id.restaurant_settings_switch_dark);
        img_edit = view.findViewById(R.id.restaurant_profile_iv_edit);

        sharedPreferences = getContext().getSharedPreferences("mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        if(nightMode){
            sw_dark.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new restaurant_eidtProfile_fragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, fragment).commit();
            }
        });

        return inflater.inflate(R.layout.fragment_restaurant_profile, container, false);
    }
}