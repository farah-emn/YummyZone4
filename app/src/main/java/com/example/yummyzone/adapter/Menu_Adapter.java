package com.example.yummyzone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.R;

import java.util.ArrayList;

/**
 *
 */
public class  Menu_Adapter extends RecyclerView.Adapter<Menu_Adapter.ViewHolder> {
    ArrayList <Menu_tab> t;
    public Menu_Adapter(ArrayList <Menu_tab> t){this.t=t;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mune_cardview,null,false
        );
         ViewHolder view_holder=new ViewHolder(v);
            return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu_tab ta=t.get(position);
        holder.item_img.setImageResource(ta.getItem_img());
        holder.icon_favorite.setImageResource(ta.getIcon_favorite());
        holder.item_name.setText(ta.getItem_name());
        holder.item_price.setText(ta.getItem_price());
        holder.pre_time.setText(ta.getPre_time());

    }

    @Override
    public int getItemCount() {
        return t.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_img,icon_favorite;
        TextView item_name,item_price,pre_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_img=itemView.findViewById(R.id.item_img);
            icon_favorite=itemView.findViewById(R.id.icon_favorite);
            item_name=itemView.findViewById(R.id.item_name);
            item_price=itemView.findViewById(R.id.item_price);
            pre_time=itemView.findViewById(R.id.pre_time);
        }
    }
}
