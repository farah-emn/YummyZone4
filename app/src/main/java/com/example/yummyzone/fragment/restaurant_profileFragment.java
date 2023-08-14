package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.example.yummyzone.activites.startedActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class restaurant_profileFragment extends Fragment {
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor;
    Switch sw_dark;
    boolean nightMode;
    ImageView img_edit;
    LinearLayout ll_logout;
    DatabaseReference restR;

    TextView tv_restaurantName;
    String resName;
    TextView tv_mobile, tv_fee, tv_time, tv_category;
    String mobile, fee, time;


    public restaurant_profileFragment(String resName) {
        this.resName = resName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_profile, container, false);
        sw_dark = view.findViewById(R.id.restaurant_settings_switch_dark);
        img_edit = view.findViewById(R.id.restaurant_profile_iv_edit);
        ll_logout = view.findViewById(R.id.restaurant_settings_ll_logout);
        tv_restaurantName = view.findViewById(R.id.restaurant_profile_tv_name);
        tv_mobile = view.findViewById(R.id.restaurant_Profile_tv_mobile);
        tv_fee = view.findViewById(R.id.restaurant_Profile_tv_fee);
        tv_time = view.findViewById(R.id.restaurant_Profile_tv_time);

        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        sharedPreferences1 = getContext().getSharedPreferences("mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences1.getBoolean("night", false);
        restR = FirebaseDatabase.getInstance().getReference().child("restaurant");



        if (resName != null) {
            tv_restaurantName.setText(resName);
        } else {
            tv_restaurantName.setText("empty");
        }

        restR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("restaurant_name").getValue().equals(resName)) {
                        mobile = keyId.child("mobilenumber").getValue().toString();
                        fee = keyId.child("delivery_fee").getValue().toString();
                        time = keyId.child("delivery_time").getValue().toString();
                    }
                    tv_mobile.setText(mobile);
                    tv_time.setText(time);
                    tv_fee.setText(fee);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ll_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(view.getContext(), startedActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        if (nightMode) {
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

        return view;
    }
}