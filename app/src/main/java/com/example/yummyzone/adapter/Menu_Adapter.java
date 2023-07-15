package com.example.yummyzone.adapter;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.classes.Cart;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.R;
import android.widget.Toast;
import com.bumptech.glide.Glide;

import com.example.yummyzone.fragment.itemInfoFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu_Adapter extends FirebaseRecyclerAdapter<Menu_tab,Menu_Adapter.myviewholder>
{ FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    String username;
    public Menu_Adapter(@NonNull FirebaseRecyclerOptions<Menu_tab> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final Menu_tab model) {
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        holder.item_name.setText(model.getItem_name());
        holder.item_price.setText(model.getItem_price());
        Glide.with(holder.item_image.getContext()).load(model.getItem_image()).into(holder.item_image);
        Glide.with(holder.icon_facorite.getContext()).load(model.getIcon()).into(holder.icon_facorite) ;
        holder.pre.setText(model.getPre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String restaurant = model.getRestaurantid();
                String item_name = model.getItem_name();
                String item_image = model.getItem_image();
                String item_price = model.getItem_price();
                String id = getRef(position).getKey();
                itemInfoFragment fragment = new itemInfoFragment(item_name, item_image, id, item_price, restaurant);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
            }
        });
        holder.icon_facorite.setOnClickListener(new View.OnClickListener() {
            private static final long DOUBLE_PRESS_INTERVAL = 250;
            private long lastPressTime;
            private boolean mHasDoubleClicked = false;
            @Override
            public void onClick(View view) {

                long pressTime = System.currentTimeMillis();
                // If double click...
                if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
                          userR.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for(DataSnapshot keyId : snapshot.getChildren()){
                                        if (keyId.child("email").getValue().equals(user.getEmail())) {
                                            username = keyId.child("username").getValue(String.class);
                                            Toast.makeText(view.getContext(), "delete", Toast.LENGTH_SHORT).show();
                                            FirebaseDatabase.getInstance().getReference().child("Foods").child(getRef(position).getKey()).child("icon").setValue("https://i.ibb.co/m571XXg/like-9.png");

                                        }}FirebaseDatabase.getInstance().getReference().child("Favourite").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                            for (DataSnapshot dataSnapshot:snapshot1.getChildren()) {
                                                FirebaseDatabase.getInstance()
                                                        .getReference()
                                                        .child("Favourite").child(username).child(getRef(position).getKey()).removeValue();}}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {}});}
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {}});mHasDoubleClicked = true;}

                else {
                    // If not double click....
                    mHasDoubleClicked = false;
                    Handler myHandler = new Handler() {
                        public void handleMessage(Message m) {
                            if (!mHasDoubleClicked) {
                                userR.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot keyId : snapshot.getChildren()){
                                            if (keyId.child("email").getValue().equals(user.getEmail())) {

                                                username = keyId.child("username").getValue(String.class);
                                                Menu_tab M=new Menu_tab(model.getItem_name(),model.getItem_image(),model.getItem_price(),model.getPre(),"https://iili.io/HPUQz3x.png");
                                                FirebaseDatabase.getInstance().getReference().child("Favourite").child(username).child(getRef(position).getKey()).setValue(M);
                                                FirebaseDatabase.getInstance().getReference().child("Foods").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                                        for (DataSnapshot dataSnapshot:snapshot1.getChildren()){

                                                            FirebaseDatabase.getInstance().getReference().child("Favourite").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot23) {
                                                                    FirebaseDatabase.getInstance().getReference().child("Foods").child(dataSnapshot.getKey()).child("icon").setValue("https://iili.io/HPgLSoX.png");
                                                                    for (DataSnapshot dataSnapshot1:snapshot23.getChildren()){
                                                                        if(dataSnapshot1.getKey().equals(dataSnapshot.getKey())){
                                                                            FirebaseDatabase.getInstance().getReference().child("Foods").child(dataSnapshot1.getKey()).child("icon").setValue("https://iili.io/HPUQz3x.png");

                                                                        }
                                                                    }}

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });


                                                        }
                                                    }




                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });

                                            }
                                        }
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {}});}
                        }
                    };Message m = new Message();myHandler.sendMessageDelayed(m,DOUBLE_PRESS_INTERVAL);}lastPressTime = pressTime;}

        });}
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mune_cardview,parent,false);
        return new myviewholder(view);
    }
    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView item_image,icon_facorite;
        TextView item_name,pre,calories,item_price;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            item_image=itemView.findViewById(R.id.item_img);
            icon_facorite=itemView.findViewById(R.id.icon_favorite);
            item_name=itemView.findViewById(R.id.item_name);
            pre=itemView.findViewById(R.id.pre_time);
            item_price=itemView.findViewById(R.id.item_price);

        }
    }

}
