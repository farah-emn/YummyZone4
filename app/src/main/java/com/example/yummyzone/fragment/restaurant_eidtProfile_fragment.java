package com.example.yummyzone.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.activites.restaurant_signUp2;
import com.example.yummyzone.classes.restaurantAccount;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class restaurant_eidtProfile_fragment extends Fragment {
    SharedPreferences sharedPreferences, sharedPreferences1;
    SharedPreferences.Editor editor;
    Switch sw_dark;
    boolean nightMode;
    ImageView img_edit;
    LinearLayout ll_logout;
    String value;
    DatabaseReference restR;
    ImageView imageView;
    EditText tv_restaurantName;
    String resName;
    EditText tv_mobile, tv_fee, tv_time, tv_category;
    String mobile, fee, time;
    private Uri imageUri;
    String img;
    Button save;
    TextView tv_text, tv_selected;
    String [] category = {"Beverages", "Burgers", "Coffee", "Desserts", "Sandwiches", "Pizza", "Sea Food"};
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<String> list1 = new ArrayList<>();

    MaterialCardView selectList;
    boolean [] selected;
    StringBuilder stringBuilder;
    HashMap<Integer, String> hash_map = new HashMap<Integer, String>();


    public restaurant_eidtProfile_fragment(String resName) {
        this.resName=resName;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_eidt_profile, container, false);
        tv_restaurantName = view.findViewById(R.id.restaurant_editProfile_tv_username);
        tv_mobile = view.findViewById(R.id.restaurant_editProfile_et_mobile);
        tv_fee = view.findViewById(R.id.restaurant_editProfile_et_fee);
        tv_time = view.findViewById(R.id.restaurant_editProfile_et_time);
        imageView=view.findViewById(R.id.restaurant_eprofile_img);
        save=view.findViewById(R.id.restaurant_editProfile_bt_save);
        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        sharedPreferences1 = getContext().getSharedPreferences("mode", Context.MODE_PRIVATE);
        nightMode = sharedPreferences1.getBoolean("night", false);

        selectList = view.findViewById(R.id.restaurant_signUp2_selectCard);
        tv_selected = view.findViewById(R.id.restaurant_signUp2_tv_selected);




        selected = new boolean[category.length];
        tv_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
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
                            hash_map.put(i,category[list.get(i)] );
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






        restR = FirebaseDatabase.getInstance().getReference().child("restaurant");

        if (resName != null) {
            tv_restaurantName.setText(resName);
        } else {
            tv_restaurantName.setText("empty");
        }

        restR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot keyId : snapshot.getChildren()) {
                    for(String string1: category ) {
                        if (keyId.child("restaurant_name").getValue().equals(resName)) {
                            mobile = keyId.child("mobilenumber").getValue().toString();
                            fee = keyId.child("delivery_fee").getValue().toString();
                            time = keyId.child("delivery_time").getValue().toString();
                            img =keyId.child("logo").getValue().toString();
                            Glide.with(imageView).load(img).into(imageView);
                            if(keyId.child("category"+string1+"_"+"id").getValue() !=null){
                                if (keyId.child("category"+string1+"_"+"id").getValue().equals(string1)){
                                    list1.add((String) keyId.child("category"+string1+"_"+"id").getValue());
                                }

                            }
                        }
                    }


                    tv_mobile.setText(mobile);
                    tv_time.setText(time);
                    tv_fee.setText(fee);


                }  tv_selected.setText(String.valueOf( list1));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                            imageView.setImageURI(imageUri);
                        } else {
                            Toast.makeText(view.getContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ph=new Intent();
                ph.setAction(Intent.ACTION_GET_CONTENT);
                ph.setType("image/*");
                activityResultLauncher.launch(ph);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "add successfuly", Toast.LENGTH_SHORT).show();
                restR.child(resName).child("restaurant_name").setValue(tv_restaurantName.getText().toString());
                restR.child(resName).child("delivery_fee").setValue(tv_fee.getText().toString());
                restR.child(resName).child("delivery_time").setValue(tv_time.getText().toString());
                restR.child(resName).child("mobilenumber").setValue(tv_mobile.getText().toString());
                if(imageUri !=null){
                    restR.child(resName).child("logo").setValue(imageUri.toString());
                }
                Iterator<Map.Entry<Integer, String>> iterator = hash_map.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer,String> pairs = (Map.Entry<Integer,String>)iterator.next();
                    value =  pairs.getValue();
                    Integer key = pairs.getKey();
                    Toast.makeText(view.getContext(), ""+value+key, Toast.LENGTH_SHORT).show();
                    for(String string1: category ) {
                        if (string1.equals(value)){
                            Toast.makeText(view.getContext(), ""+value, Toast.LENGTH_SHORT).show();
                            restR.child(resName).child("category"+value+"_"+"id").setValue(value);
                        }}}
                Fragment fragment = new restaurant_profileFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, fragment).commit();
            }
        });

        return view;
    }
}