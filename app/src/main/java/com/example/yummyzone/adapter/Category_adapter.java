package com.example.yummyzone.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.yummyzone.classes.Category_tab;
import com.example.yummyzone.R;
import com.example.yummyzone.fragment.homeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Category_Adapter extends FirebaseRecyclerAdapter<Category_tab, Category_Adapter.myviewholder>
{
    public Category_Adapter(@NonNull FirebaseRecyclerOptions<Category_tab> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final Category_tab model) {
        holder.name_category.setText(model.getName());
        Glide.with(holder.image_category.getContext()).load(model.getImage()).into(holder.image_category);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String  position_id = getRef(position).getKey();
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new homeFragment(position_id)).commit();
            }});}

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_design,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView image_category;
        TextView name_category;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image_category=itemView.findViewById(R.id.tab_image);
            name_category=itemView.findViewById(R.id.tab_name);


        }

        @Override
        public void onClick(View view) {


        }
    }

}

//package com.example.yummyzone.adapter;
//
//import android.annotation.SuppressLint;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
//import com.example.yummyzone.classes.Category_tab;
//import com.example.yummyzone.R;
//import com.example.yummyzone.fragment.homeFragment;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class Category_Adapter extends FirebaseRecyclerAdapter<Category_tab, Category_Adapter.myviewholder>
//{
//    public Category_Adapter(@NonNull FirebaseRecyclerOptions<Category_tab> options) {
//        super(options);
//    }
//    @Override
//    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final Category_tab model) {
//        holder.name_category.setText(model.getName());
//        Glide.with(holder.image_category.getContext()).load(model.getImage()).into(holder.image_category);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String  position_id = getRef(position).getKey();
//                AppCompatActivity activity=(AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new homeFragment(position_id)).commit();
//            }});}
//    @NonNull
//    @Override
//    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_design,parent,false);
//        return new myviewholder(view);
//    }
//
//    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
//    {
//        ImageView image_category;
//        TextView name_category;
//        public myviewholder(@NonNull View itemView) {
//            super(itemView);
//            image_category=itemView.findViewById(R.id.tab_image);
//            name_category=itemView.findViewById(R.id.tab_name);
//
//
//        }
//
//        @Override
//        public void onClick(View view) {
//
//
//        }
//    }
//
//}