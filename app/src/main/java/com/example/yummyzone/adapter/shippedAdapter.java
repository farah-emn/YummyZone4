package com.example.yummyzone.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.restaurant;
import com.example.yummyzone.classes.shipped;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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
//        Picasso.get().load(sh.getImg()).into(holder.image);
        Glide.with(context).load(sh.getImg()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return shippedList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_restaurantName, tv_date, tv_price, tv_address, tv_mobileNumber;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.shipped_img);
            tv_restaurantName = itemView.findViewById(R.id.shipped_tv_restaurantName);
            tv_date = itemView.findViewById(R.id.shipped_tv_date);
            tv_price = itemView.findViewById(R.id.shipped_tv_price);
            tv_address = itemView.findViewById(R.id.shipped_tv_address);
            tv_mobileNumber = itemView.findViewById(R.id.shipped_tv_mobileNumber);

        }
    }
}

