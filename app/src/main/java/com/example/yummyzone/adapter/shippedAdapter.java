package com.example.yummyzone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.shipped;

import java.util.ArrayList;

public class shippedAdapter extends RecyclerView.Adapter<shippedAdapter.myviewholder> {
    ArrayList<shipped> shippedList;
    Context context;

    public shippedAdapter(Context context, ArrayList<shipped> shippedList){
        this.context = context;
        this.shippedList = shippedList;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shipped_order_card, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        shipped sh = shippedList.get(position);
        holder.tv_price.setText(sh.getPrice());
        holder.tv_restaurantName.setText(sh.getRestaurantName());
        holder.tv_mobileNumber.setText(sh.getMobile());
        holder.tv_date.setText(sh.getDate());
        if (sh.getState().equals("received")){
            holder.tv_state.setTextColor(Color.parseColor("#32cd32"));
            holder.tv_state.setText(sh.getState());
        } else if (sh.getState().equals("notReceived")) {
            holder.tv_state.setTextColor(Color.parseColor("#ed1c24"));
            holder.tv_state.setText(sh.getState());
        }else {
            holder.tv_state.setText(sh.getState());
        }


        Glide.with(holder.image.getContext()).load(sh.getImg()).into(holder.image) ;
        holder.tv_address.setText(sh.getCity()+"-"+sh.getDistrict()+"-"+sh.getStreet());

    }

    @Override
    public int getItemCount() {
        return shippedList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_restaurantName, tv_date, tv_price, tv_address, tv_mobileNumber, tv_state;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shipped_img);
            tv_restaurantName = itemView.findViewById(R.id.shipped_tv_restaurantName);
            tv_date = itemView.findViewById(R.id.shipped_tv_date);
            tv_price = itemView.findViewById(R.id.shipped_tv_price);
            tv_address = itemView.findViewById(R.id.shipped_tv_address);
            tv_mobileNumber = itemView.findViewById(R.id.shipped_tv_mobileNumber);
            tv_state = itemView.findViewById(R.id.shipped_tv_state);

        }
    }
}

