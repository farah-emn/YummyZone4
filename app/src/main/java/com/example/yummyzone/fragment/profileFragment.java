package com.example.yummyzone.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.activites.signIn;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class profileFragment extends Fragment {

    Switch sw_dark;
    boolean nightMode;
    Button bt_edit;
    ConstraintLayout bt_logout;
    LinearLayout unpaid;
    LinearLayout processing;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    TextView tv_name;
    TextView tv_email;
    ImageView img_edit;
    String name;
    String email;
    ImageView img_profile;
    String username;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sw_dark = view.findViewById(R.id.settings_switch_dark);
        //bt_edit=view.findViewById(R.id.proile_bt_edit);
        bt_logout = view.findViewById(R.id.profile_constrain_logout);
        tv_name = view.findViewById(R.id.profile_tv_name);
        tv_email = view.findViewById(R.id.profile_tv_email);
        unpaid = view.findViewById(R.id.profile_linear_unpaid);
        img_edit= view.findViewById(R.id.profile_iv_edit);
        processing = view.findViewById(R.id.profile_linear_processing);
        img_profile = view.findViewById(R.id.profile_img);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/");




        sharedPreferences = getContext().getSharedPreferences("mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);
        if(nightMode){
            sw_dark.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new eidtProfileFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
            }
        });
        processing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new processingFragment();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, fragment).commit();
            }
        });
        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId : snapshot.getChildren()){
                    if (keyId.child("email").getValue().equals(user.getEmail())){
                        name = keyId.child("firstName").getValue(String.class);
                        email = keyId.child("email").getValue(String.class);
                        username = keyId.child("username").getValue(String.class);
                    }
                }
                tv_name.setText(name);
                tv_email.setText(email);
                storageReference.child(username).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(getActivity()).load(uri).into(img_profile);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        bt_edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Fragment fragment = new eidtProfileFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
//            }
//        });

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

        unpaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new cartFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
            }
        });
        return view;
        }}