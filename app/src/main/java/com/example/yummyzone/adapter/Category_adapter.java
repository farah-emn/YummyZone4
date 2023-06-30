package com.example.yummyzone.adapter;

import android.os.Bundle;
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

public class Category_adapter extends FirebaseRecyclerAdapter<Category_tab, Category_adapter.myviewholder>
{ public Category_adapter(@NonNull FirebaseRecyclerOptions<Category_tab> options) {
        super(options);}
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final Category_tab model) {
        holder.nametext.setText(model.getName());

        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
        holder.nametext.setOnClickListener(new View.OnClickListener() {
            String post_key1 = getRef(position).getKey();
            @Override
            public void onClick(View view) {
                homeFragment fragment=new homeFragment(post_key1);
                Bundle bundle=new Bundle();
                bundle.putString("cate1",post_key1);
                fragment.setArguments(bundle);
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,fragment).commit();

            }});}

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_design,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView img1;
        TextView nametext;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img1=itemView.findViewById(R.id.tab_image);
            nametext=itemView.findViewById(R.id.tab_name);
        }
        @Override
        public void onClick(View view) {}}

}