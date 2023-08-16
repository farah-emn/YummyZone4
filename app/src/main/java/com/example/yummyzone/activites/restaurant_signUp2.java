package com.example.yummyzone.activites;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yummyzone.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class restaurant_signUp2 extends AppCompatActivity {
    Button bt_signUp;
    DatabaseReference restR, rootR;
    FirebaseAuth mAuth;
    EditText et_mobile, et_time, et_fee;
    String mobile, time, fee, comNum, resName, password;
    TextView tv_text, tv_selected;
    String [] category = {"Beverages", "Burgers", "Coffee", "Desserts", "Sandwiches", "Pizza", "Sea Food"};
    ArrayList<Integer> list = new ArrayList<>();
    MaterialCardView selectList;
    boolean [] selected;
    StringBuilder stringBuilder;
    HashMap<Integer, String> hash_map = new HashMap<Integer, String>();
    String value="k";
    ImageView imageView;
    private Uri imageUri;
    final  private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sign_up2);
        getSupportActionBar().hide();
        bt_signUp = findViewById(R.id.restaurant_signUp2_bt_signUp);
        et_mobile = findViewById(R.id.restaurant_signUp2_et_mobile);
        et_time = findViewById(R.id.restaurant_signUp2_et_deliveryTime);
        et_fee = findViewById(R.id.restaurant_signUp2_et_deliveryFee);
        tv_text = findViewById(R.id.restaurant_signUp2_tv_text);
        selectList = findViewById(R.id.restaurant_signUp2_selectCard);
        tv_selected = findViewById(R.id.restaurant_signUp2_tv_selected);
        imageView=findViewById(R.id.restaurant_signup2_profile_img);
        progressBar = findViewById(R.id.restaurant_signUp2_pro);
        progressBar.setVisibility(View.GONE);

        comNum = getIntent().getStringExtra("comNum");
        resName = getIntent().getStringExtra("resName");
        password = getIntent().getStringExtra("password");
        mAuth = FirebaseAuth.getInstance();
        restR = FirebaseDatabase.getInstance().getReference().child("restaurant");
        rootR = FirebaseDatabase.getInstance().getReference();


        selected = new boolean[category.length];
        tv_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(restaurant_signUp2.this);
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

        bt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = et_mobile.getText().toString();
                fee = et_fee.getText().toString();
                time = et_time.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                restR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(resName).exists()) {
                            // progressBar.setVisibility(View.GONE);
                            tv_text.setText("The restaurant name is already exit");
                            progressBar.setVisibility(View.GONE);
                        } else {
                            if (!mobile.equals("") && !fee.equals("") && !time.equals("")&&imageUri != null ) {

                                Iterator<Map.Entry<Integer, String>> iterator = hash_map.entrySet().iterator();
                                while (iterator.hasNext()) {
                                    Map.Entry<Integer,String> pairs = (Map.Entry<Integer,String>)iterator.next();
                                    value =  pairs.getValue();
                                    Integer key = pairs.getKey();
                                    Toast.makeText(restaurant_signUp2.this, ""+value+key, Toast.LENGTH_SHORT).show();
                                    for(String string1: category ) {
                                        if (string1.equals(value)){
                                            Toast.makeText(restaurant_signUp2.this, ""+value, Toast.LENGTH_SHORT).show();
                                            if (imageUri != null){
                                                FirebaseDatabase.getInstance().getReference().child("restaurant").child(resName).child("item_image").setValue(imageUri.toString());
                                            }
                                            restR.child(resName).child("commercial_register").setValue(comNum);
                                            restR.child(resName).child("password").setValue(password);
                                            restR.child(resName).child("restaurant_name").setValue(resName);
                                            restR.child(resName).child("delivery_fee").setValue(fee);
                                            restR.child(resName).child("delivery_time").setValue(time);
                                            restR.child(resName).child("mobilenumber").setValue(mobile);
                                            restR.child(resName).child("password").setValue(password);
                                            restR.child(resName).child("category"+value+"_"+"id").setValue(value);
                                        }}

                                    //    restaurantAccount resAccount = new restaurantAccount(resName, password, comNum, fee, time, mobile, value,"4f");
                                }
                                Toast.makeText(restaurant_signUp2.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(restaurant_signUp2.this, mainRestaurantActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("restaurantName", resName);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();


                            }else {
                                tv_text.setText("Please enter all fields");
                                progressBar.setVisibility(View.GONE);

                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

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
                            Toast.makeText(restaurant_signUp2.this, "No Image Selected", Toast.LENGTH_SHORT).show();

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


    } private void uploadToFirebase(Uri uri, String resName){
        final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        FirebaseDatabase.getInstance().getReference().child("restaurant").child(resName).child("item_image").setValue(uri.toString());

                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }
}