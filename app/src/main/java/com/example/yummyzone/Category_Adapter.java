package com.example.yummyzone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.tabViewHolder> {
   ArrayList<Category_tab> t;
   public Category_Adapter(ArrayList <Category_tab> t){
       this.t=t;
   }
    @NonNull
    @Override
    public tabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_tab_design,null,false
    );
        tabViewHolder view_holder=new tabViewHolder(v);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull tabViewHolder holder, int position) {
Category_tab ta=t.get(position);
holder.category_name.setText(ta.getTitle());
holder.category_image.setImageResource(ta.getImage());
    }

    @Override
    public int getItemCount() {
        return t.size();
    }
   class tabViewHolder extends RecyclerView.ViewHolder{
       TextView category_name;
       ImageView category_image;
       public tabViewHolder(@NonNull View itemView) {
           super(itemView);
      category_name=itemView.findViewById(R.id.tab_name);
      category_image=itemView.findViewById(R.id.tab_image);


       }
   }
}
