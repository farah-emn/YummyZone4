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
import com.example.yummyzone.classes.shippedState;
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

public class shippedStateAdapter extends RecyclerView.Adapter<shippedStateAdapter.myviewholder>{
    ArrayList<shippedState> shippedStateList;
    Context context;
    DatabaseReference orderR, rootR, userR;
    String resName;
    String username;

    public shippedStateAdapter(Context context, ArrayList<shippedState> shippedStateList, String resName){
        this.context = context;
        this.shippedStateList = shippedStateList;
        this.resName = resName;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shipped_state_cart, parent, false);
        rootR = FirebaseDatabase.getInstance().getReference();
        orderR = rootR.child("order");
        userR = rootR.child("user");
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull shippedStateAdapter.myviewholder holder, int position) {
        shippedState sh = shippedStateList.get(position);
        holder.tv_orderNumber.setText(sh.getOrderNumber());
        holder.tv_date.setText(sh.getDate());
        holder.tv_price.setText(sh.getPrice());
        holder.tv_address.setText(sh.getAddress());
        holder.tv_mobileNumber.setText(sh.getMobileNumber());

        holder.tv_received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderR.child(sh.getOrderNumber()).child("orderState").setValue("received");
                restaurant_ordersFragment newF = new restaurant_ordersFragment();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, newF).commit();
            }
        });


        holder.tv_notReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderR.child(sh.getOrderNumber()).child("orderState").setValue("notReceived");
                
                orderR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot keyId : snapshot.getChildren()) {
                            if(keyId.child("orderNumber").getValue().equals(sh.getOrderNumber())){
                                username = keyId.child("username").getValue().toString();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                userR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot keyId : snapshot.getChildren()) {
                            if(keyId.child("username").getValue().equals(username)){
                                String num = keyId.child("accountState").getValue().toString();
                                if (num.equals("zero")){
                                    userR.child(username).child("accountState").setValue("one");
                                }else if (num.equals("one")){
                                    userR.child(username).child("accountState").setValue("two");
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                restaurant_ordersFragment newF = new restaurant_ordersFragment();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment, newF).commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return shippedStateList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView tv_orderNumber, tv_date, tv_price, tv_address, tv_mobileNumber, tv_received, tv_notReceived;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tv_orderNumber = itemView.findViewById(R.id.restaurant_shippedState_tv_orderNumber);
            tv_date = itemView.findViewById(R.id.restaurant_shippedState_tv_date);
            tv_price = itemView.findViewById(R.id.restaurant_shippedState_tv_price);
            tv_address = itemView.findViewById(R.id.restaurant_shippedState_tv_address);
            tv_mobileNumber = itemView.findViewById(R.id.restaurant_shippedState_tv_mobile);
            tv_received = itemView.findViewById(R.id.restaurant_shippedState_tv_received);
            tv_notReceived = itemView.findViewById(R.id.restaurant_shippedState_tv_notReceived);


        }
    }
}
