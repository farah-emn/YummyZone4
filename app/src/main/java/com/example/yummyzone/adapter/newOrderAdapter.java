package com.example.yummyzone.adapter;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.newOrder;
import com.example.yummyzone.classes.order2;
import com.example.yummyzone.classes.shipped;
import com.example.yummyzone.fragment.cartFragment;
import com.example.yummyzone.fragment.newFragment;
import com.example.yummyzone.fragment.restaurant_orderDetails_fragment;
import com.example.yummyzone.fragment.restaurant_ordersFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class newOrderAdapter extends RecyclerView.Adapter<newOrderAdapter.myviewholder>{
    ArrayList<newOrder> newOrderList;
    Context context;
    DatabaseReference orderR, rootR;
    String resName;

    public newOrderAdapter(Context context, ArrayList<newOrder> newOrderList, String resName){
        this.context = context;
        this.newOrderList = newOrderList;
        this.resName = resName;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_order_cart, parent, false);
        rootR = FirebaseDatabase.getInstance().getReference();
        orderR = rootR.child("order");
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newOrderAdapter.myviewholder holder, int position) {
        newOrder sh = newOrderList.get(position);
        holder.tv_orderNumber.setText(sh.getOrderNumber());
        holder.tv_date.setText(sh.getDate());
        holder.tv_price.setText(sh.getPrice());
        holder.tv_address.setText(sh.getAddress());
        holder.tv_mobileNumber.setText(sh.getMobileNumber());

        holder.tv_ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                orderR.child(sh.getOrderNumber()).child("orderState").setValue("shipped");
                restaurant_ordersFragment newF = new restaurant_ordersFragment();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, newF).commit();

            }
        });

        holder.tv_seeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //restaurant_orderDetails_fragment orderFragment = new restaurant_orderDetails_fragment(sh.getOrderNumber(), sh.getDate(), sh.getPrice(), sh.getAddress(), sh.getMobileNumber());
                restaurant_orderDetails_fragment orderFragment = new restaurant_orderDetails_fragment(sh.getOrderNumber(), sh.getDate(), sh.getPrice(), sh.getAddress(), sh.getMobileNumber());
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, orderFragment).commit();
            }
        });

        holder.tv_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                orderR.child(sh.getOrderNumber()).child("orderState").setValue("reject");
                restaurant_ordersFragment newF = new restaurant_ordersFragment();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, newF).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return newOrderList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView tv_orderNumber, tv_date, tv_price, tv_address, tv_mobileNumber, tv_reject, tv_seeDetails, tv_ready;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tv_orderNumber = itemView.findViewById(R.id.restaurant_newOrderCart_tv_orderNumber);
            tv_date = itemView.findViewById(R.id.restaurant_newOrderCart_tv_orderDate);
            tv_price = itemView.findViewById(R.id.restaurant_newOrderCart_tv_orderPrice);
            tv_address = itemView.findViewById(R.id.restaurant_newOrderCart_tv_orderAddress);
            tv_mobileNumber = itemView.findViewById(R.id.restaurant_newOrderCart_tv_mobile);
            tv_reject = itemView.findViewById(R.id.restaurant_newOrderCart_tv_reject);
            tv_seeDetails = itemView.findViewById(R.id.restaurant_newOrderCart_tv_seeDetails);
            tv_ready = itemView.findViewById(R.id.restaurant_newOrderCart_tv_ready);


        }
    }
}
