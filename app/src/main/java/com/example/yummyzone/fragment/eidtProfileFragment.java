package com.example.yummyzone.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.user;
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
    DatabaseReference userR, rootR, newR;
    FirebaseDatabase database;
    String username;
    String firstName;
    String lastName;
    String mobileNumber;
    String City;
    String street;
    String district;
    Button bt_save;
    String email;
    String password;
    ImageView img_profile;
    Uri img_uri;
    FirebaseStorage storage;
    StorageReference storageReference;




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
        img_profile = view.findViewById(R.id.profile_img);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        storage= FirebaseStorage.getInstance();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/");
        ProgressBar progressBar = new ProgressBar(getContext());


        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        if(uri != null){
                            img_uri = uri;
                            img_profile.setImageURI(uri);

                        }
                    }
                });


        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });

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
                        email = keyId.child("email").getValue(String.class);
                        password = keyId.child("password").getValue(String.class);
                    }
                }
                tv_username.setText(username);
                et_firstName.setText(firstName);
                et_lastName.setText(lastName);
                et_city.setText(City);
                et_street.setText(street);
                et_mobileNumber.setText(mobileNumber);
                et_district.setText(district);

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

                bt_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ed_firstName =String.valueOf(et_firstName.getText()) ;
                        String ed_lastName = et_lastName.getText().toString();
                        String ed_mobileNumber = et_mobileNumber.getText().toString();
                        String ed_city = et_city.getText().toString();
                        String ed_street = et_street.getText().toString();
                        String ed_district = et_district.getText().toString();
                        if (img_uri != null) {
                            FirebaseStorage.getInstance().getReference("images/" + username).putFile(img_uri);
                        }
                        user u = new user(username, email, password, ed_firstName, ed_lastName, ed_mobileNumber, ed_city, ed_street, ed_district);
                        userR.child(String.valueOf(username)).setValue(u);
                        Fragment fragment = new profileFragment();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }

}