package com.example.yummyzone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.tab;

import java.util.ArrayList;

public class tabAdapter extends RecyclerView.Adapter<tabAdapter.tabViewHolder> {
    ArrayList<tab> t;
    public tabAdapter(ArrayList<tab> t) {
        this.t = t;
    }

    @NonNull
    @Override
    public tabAdapter.tabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_design, null, false);
        tabViewHolder vh =new tabViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull tabAdapter.tabViewHolder holder, int position) {
        tab ta= t.get(position);
        holder.name.setText(ta.getName());
        holder.image.setImageResource(ta.getImage());

    }

    @Override
    public int getItemCount() {
        return t.size();
    }

    class tabViewHolder extends RecyclerView.ViewHolder{
        TextView name ;
        ImageView image;
        public tabViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tab_name);
            image = itemView.findViewById(R.id.tab_image);
        }
    }
}
