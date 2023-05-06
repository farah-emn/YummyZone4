package com.example.yummyzone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yummyzone.classes.cartItem;

import com.example.yummyzone.R;

import java.util.ArrayList;

public class cartItemAdapter extends RecyclerView.Adapter<cartItemAdapter.cartItemViewHolder> {
    ArrayList<cartItem> items;


    public cartItemAdapter(ArrayList<cartItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public cartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, null, false);
        cartItemViewHolder vh = new cartItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull cartItemViewHolder holder, int position) {
        cartItem item = items.get(position);
        holder.item_image.setImageResource(item.getItem_image());
        holder.price.setText(item.getPrice());
        holder.name.setText(item.getName());
        holder.number.setText(item.getNumber());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class cartItemViewHolder extends RecyclerView.ViewHolder{
        ImageView item_image;
        TextView name;
        TextView number;
        TextView price;
        public cartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.itemInfo_iv_itemImage);
            name = itemView.findViewById(R.id.itemInfo_tv_itemName);
            number = itemView.findViewById(R.id.itemInfo_tv_itemNumber);
            price = itemView.findViewById(R.id.itemInfo_tv_price);
        }
    }
}
