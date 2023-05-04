package com.example.yummyzone.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yummyzone.R;

public class itemInfo extends AppCompatActivity {
    TextView tv_restaurantName;
    TextView tv_itemName;
    TextView tv_itemNumber;
    TextView tv_description;
    TextView tv_time;
    TextView tv_price;
    TextView tv_cal;
    ImageView iv_like;
    ImageView iv_back;
    ImageView iv_item;
    ImageView iv_minus;
    ImageView iv_plus;
    Button bt_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_info);

        tv_restaurantName = findViewById(R.id.itemInfo_tv_restaurantNme);
        tv_itemName = findViewById(R.id.itemInfo_tv_itemName);
        tv_itemNumber = findViewById(R.id.itemInfo_tv_itemNumber);
        tv_description = findViewById(R.id.itemInfo_tv_description);
        tv_time = findViewById(R.id.itemInfo_tv_time);
        tv_price = findViewById(R.id.itemInfo_tv_price);
        tv_cal = findViewById(R.id.itemInfo_tv_cal);
        iv_like = findViewById(R.id.itemInfo_image_like);
        iv_back = findViewById(R.id.itemInfo_image_back);
        iv_item = findViewById(R.id.itemInfo_image_item);
        iv_minus = findViewById(R.id.itemInfo_image_minus);
        iv_plus = findViewById(R.id.itemInfo_image_plus);
        bt_add = findViewById(R.id.itemInfo_bt_add);
    }
}