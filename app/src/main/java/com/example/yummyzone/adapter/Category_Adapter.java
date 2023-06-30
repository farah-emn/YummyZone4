package com.example.yummyzone.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.classes.Category_tab;
import com.example.yummyzone.R;
import com.example.yummyzone.fragment.homeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Category_Adapter extends FirebaseRecyclerAdapter<Category_tab, Category_Adapter.myviewholder>
{
    Context context;




    public Category_Adapter(@NonNull FirebaseRecyclerOptions<Category_tab> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final Category_tab model) {
        holder.nametext.setText(model.getName());
        final String  post_key = getRef(position).getKey();
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);
        holder.nametext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   AppCompatActivity activity=(AppCompatActivity)view.getContext();
                Fragment fragment = new homeFragment(post_key);
                //     Intent f=new Intent(view.getContext(), MainActivity.class);
                //       f.putExtra("cate",post_key);
                // view.getContext().startActivity(f);
                Bundle bundle=new Bundle();
                bundle.putString("cate",post_key);
                //  homeFragment h=new homeFragment();
                fragment.setArguments(bundle);
                //getSupportFragmentManager().beginTransaction();
                AppCompatActivity activity=(AppCompatActivity)view.getContext();

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new homeFragment(post_key)).commit();
                //       .add(R.id.RecyclerViewCategory,h);

                //     getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new descfragment(model.getName(),model.getCourse(),model.getEmail(),model.getPurl())).addToBackStack(null).commit();
                //ff.putExtra("cat",post_key);
                //view.getContext().startActivity(f);
                //     Toast.makeText(view.getContext(), "kk"+post_key, Toast.LENGTH_SHORT).show();
                //
            }
        });

    }

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
        public void onClick(View view) {


        }
    }

}