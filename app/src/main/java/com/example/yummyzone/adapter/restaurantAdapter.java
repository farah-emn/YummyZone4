package com.example.yummyzone.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.activites.MainActivity;
import com.example.yummyzone.activites.MenuActivity;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.classes.restaurant;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;


public class restaurantAdapter extends FirebaseRecyclerAdapter<restaurant,restaurantAdapter.myviewholder>
{
    public  restaurantAdapter(@NonNull FirebaseRecyclerOptions<restaurant> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final restaurant model) {
        holder.name.setText(model.getName());
        Glide.with(holder.image.getContext()).load(model.getImage()).into(holder.image);
        final String  post_key = getRef(position).getKey();
        //holder.pre.setText(model.getPre());
        //holder.price.setText(model.getPrice());
        //  Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Menu fragment=new Menu();
//                Bundle bundle=new Bundle();
//                bundle.putString("cate",post_key);
//                //  homeFragment h=new homeFragment();
//                fragment.setArguments(bundle);
//                //getSupportFragmentManager().beginTransaction();
//                AppCompatActivity activity=(AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,fragment).commit();
//            }
//        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_cardview,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        TextView name,pre,calories,price;
        ImageView image;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.homeScreen_restaurant_image);
            name=itemView.findViewById(R.id.homeScreen_tv_restaurantName);


        }
    }

}
