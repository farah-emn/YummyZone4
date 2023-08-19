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

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.Menu_tab;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class restaurant_editFragment extends Fragment {
    TextView item_name,item_price,prepration_time,description,Calories;
    DatabaseReference restR;
    ImageView imageView,delete_itemname,delete_itemprice,delete_pretime,delete_calories,delete_description;
    private Uri imageUri;
    Button save;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public restaurant_editFragment() {
        // Required empty public constructor
    }

    public static restaurant_editFragment newInstance(String param1, String param2) {
        restaurant_editFragment fragment = new restaurant_editFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
       View view= inflater.inflate(R.layout.fragment_restaurant_edit, container, false);
        Bundle args = getArguments();
        String restaurant_id=args.getString("item_name");
        String itemprice=args.getString("item_price");
        String pre_time=args.getString("pre");
        String Description=args.getString("desc");
        String item_id=args.getString("item_id");
        String res_id=args.getString("res_id");
        String calories=args.getString("calories");
        String img_item=args.getString("item_image");
        save=view.findViewById(R.id.save);
        imageView=view.findViewById(R.id.restaurantedit_img);
        item_name=view.findViewById(R.id.restaurant_et_itemName);
        item_price=view.findViewById(R.id.restaurant_et_price);
        prepration_time=view.findViewById(R.id.restaurant_et_time);
        description=view.findViewById(R.id.description);
        Calories=view.findViewById(R.id.restaurant_et_calories);
        delete_itemname=view.findViewById(R.id.delete_item_name);
        delete_itemprice=view.findViewById(R.id.delete_item_price);
        delete_pretime=view.findViewById(R.id.delete_preparation_time);
        delete_calories=view.findViewById(R.id.delete_calories);
        delete_description=view.findViewById(R.id.delete_description);
        item_name.setText(restaurant_id);
        item_price.setText(itemprice);
        prepration_time.setText(pre_time);
        description.setText(Description);
        Calories.setText(calories);
        Glide.with(imageView).load(img_item).into(imageView);
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
                if(item_name.getText()!=""&&item_price.getText()!=""&&prepration_time.getText()!=""&&img_item !=""&&Calories.getText() !=""){
                    Toast.makeText(view.getContext(), "add successfuly", Toast.LENGTH_SHORT).show();
                    Menu_tab menu_tab=new Menu_tab(String.valueOf(item_name.getText()),String.valueOf(item_price.getText()),String.valueOf(prepration_time.getText()),String.valueOf(description.getText()),res_id);
                    FirebaseDatabase.getInstance().getReference().child("Foods").child(String.valueOf(res_id+"_"+item_name.getText())).child("calories").setValue(calories);
                    restR = FirebaseDatabase.getInstance().getReference().child("Foods");
                    restR.child(String.valueOf(res_id+"_"+item_name.getText())).setValue(menu_tab);
                    if(imageUri ==null){
                        FirebaseDatabase.getInstance().getReference().child("Foods").child(String.valueOf(res_id+"_"+item_name.getText())).child("item_image").setValue(img_item);
                    }else {
                    FirebaseDatabase.getInstance().getReference().child("Foods").child(String.valueOf(res_id+"_"+item_name.getText())).child("item_image").setValue(imageUri.toString());
                }}

                else if (item_name.getText()==""||item_price.getText()==""||prepration_time.getText()==""||imageUri == null||Calories.getText() =="") {
                    Toast.makeText(view.getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                }



            }
        });
        delete_itemname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_name.setText("");
            }
        });
        delete_itemprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_price.setText("");
            }
        });
        delete_calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calories.setText("");
            }
        });
        delete_pretime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepration_time.setText("");
            }
        });

        delete_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description.setText("");

    }
});






            return  view;}
}