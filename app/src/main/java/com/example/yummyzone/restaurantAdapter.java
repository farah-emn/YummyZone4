package com.example.yummyzone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class restaurantAdapter extends RecyclerView.Adapter<restaurantAdapter.restaurantViewHolder> {
    ArrayList<restaurant> restaurants;

    public restaurantAdapter(ArrayList<restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public restaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_cardview, null, false);
        restaurantViewHolder vh = new restaurantViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull restaurantViewHolder holder, int position) {
        restaurant r = restaurants.get(position);
        holder.restaurant_name.setText(r.getRestaurant_name());
        holder.description.setText(r.getDescription());
        holder.price.setText(r.getPrice());
        holder.restaurant_image.setImageResource(r.getRestaurant_image());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    class restaurantViewHolder extends RecyclerView.ViewHolder{
        ImageView restaurant_image;
        TextView restaurant_name;
        TextView description;
        TextView price;

        public restaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurant_image = itemView.findViewById(R.id.homeScreen_restaurant_image);
            restaurant_name = itemView.findViewById(R.id.homeScreen_tv_restaurantName);
            description = itemView.findViewById(R.id.homeScreen_tv_description);
            price = itemView.findViewById(R.id.homeScreen_tv_price);
        }
    }
}
