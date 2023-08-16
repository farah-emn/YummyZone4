package com.example.yummyzone.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.Menu_tab;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class restaurant_AddFragment extends Fragment {
    DatabaseReference restR, rootR;
    TextView item_name,item_price,prepration_time,description,calories;
    Button B;
    ImageView imageView;
    private Uri imageUri;
    final  private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();



    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String res;

    public restaurant_AddFragment(String res) {
        this.res=res;
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_restaurant__add, container, false);
        item_name=view.findViewById(R.id.restaurantAdd_et_itemName);
        item_price=view.findViewById(R.id.restaurantAdd_et_price);
        prepration_time=view.findViewById(R.id.restaurantAdd_et_time);
        description=view.findViewById(R.id.description);
        calories=view.findViewById(R.id.restaurantAdd_et_calories);
        B=view.findViewById(R.id.add);
        imageView=view.findViewById(R.id.restaurantAdd_img);

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


        B.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(item_name.getText()!=""&&item_price.getText()!=""&&prepration_time.getText()!=""&&imageUri != null&&calories.getText() !=""){
                    Toast.makeText(view.getContext(), "add successfuly", Toast.LENGTH_SHORT).show();
                    Menu_tab menu_tab=new Menu_tab(String.valueOf(item_name.getText()),String.valueOf(item_price.getText()),String.valueOf(prepration_time.getText()),String.valueOf(description.getText()),res);
                    restR = FirebaseDatabase.getInstance().getReference().child("Foods");
                    FirebaseDatabase.getInstance().getReference().child("Foods").child(String.valueOf(res+"_"+item_name.getText())).setValue(menu_tab);
                    FirebaseDatabase.getInstance().getReference().child("Foods").child(String.valueOf(res+"_"+item_name.getText())).child("item_image").setValue(imageUri.toString());
                    FirebaseDatabase.getInstance().getReference().child("Foods").child(String.valueOf(res+"_"+item_name.getText())).child("calories").setValue(String.valueOf(calories.getText()));

                    item_name.setText("");
                    item_price.setText("");
                    description.setText("");
                    prepration_time.setText("");
                    calories.setText("");
                    imageView.setImageResource(R.drawable.image);


                } else if (item_name.getText()==""||item_price.getText()==""||prepration_time.getText()==""||imageUri == null||calories.getText() =="") {
                    Toast.makeText(view.getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view; }
}