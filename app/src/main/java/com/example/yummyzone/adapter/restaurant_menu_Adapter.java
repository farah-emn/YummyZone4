package com.example.yummyzone.adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.fragment.restaurant_editFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class restaurant_menu_Adapter extends FirebaseRecyclerAdapter<Menu_tab,restaurant_menu_Adapter.myviewholder>
{ FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    String username;
    DatabaseReference restR;

    public restaurant_menu_Adapter(@NonNull FirebaseRecyclerOptions<Menu_tab> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final Menu_tab model) {

        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        holder.item_name.setText(model.getItem_name());
        holder.item_price.setText(model.getItem_price()+" "+"SR");
        Glide.with(holder.item_image.getContext()).load(model.getItem_image()).into(holder.item_image);
        holder.pre.setText(model.getPreparation_time()+" "+"Min");
        holder.cal.setText(model.getCalories());
        holder.description.setText(model.getDescription());
        restR = FirebaseDatabase.getInstance().getReference().child("restaurant");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "delete succesfuly", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("Foods").child(String.valueOf( getRef(position).getKey())).removeValue();}});

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurant_editFragment fragment=new restaurant_editFragment();
                Bundle bundle=new Bundle();
                bundle.putString("item_id", getRef(position).getKey());
                bundle.putString("res_id", model.getRestaurantid());
                bundle.putString("item_name", model.getItem_name());
                bundle.putString("item_price", model.getItem_price());
                bundle.putString("item_image", model.getItem_image());
                bundle.putString("pre", model.getPreparation_time());
                bundle.putString("desc", model.getDescription());
                bundle.putString("calories", model.getCalories());

                fragment.setArguments(bundle);
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.restaurant_main_fragment,fragment).commit();


            }
        });
        /*   holder.icon_facorite.setOnClickListener(new View.OnClickListener() {
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
                                    FirebaseDatabase.getInstance().getReference().child("Foods").child(getRef(position).getKey()).child("favorite_icon").setValue("https://i.ibb.co/m571XXg/like-9.png");

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
                                                Menu_tab M=new Menu_tab(model.getItem_name(),model.getItem_image(),model.getItem_price(),model.getPreparation_time(),"https://iili.io/HPUQz3x.png",model.getCalories(),model.getDescription(),model.getRestaurantid());
                                                FirebaseDatabase.getInstance().getReference().child("Favourite").child(username).child(getRef(position).getKey()).setValue(M);
                                                FirebaseDatabase.getInstance().getReference().child("Foods").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot1) {
                                                        for (DataSnapshot dataSnapshot:snapshot1.getChildren()){

                                                            FirebaseDatabase.getInstance().getReference().child("Favourite").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot23) {
                                                                    FirebaseDatabase.getInstance().getReference().child("Foods").child(dataSnapshot.getKey()).child("favorite_icon").setValue("https://iili.io/HPgLSoX.png");
                                                                    for (DataSnapshot dataSnapshot1:snapshot23.getChildren()){
                                                                        if(dataSnapshot1.getKey().equals(dataSnapshot.getKey())){
                                                                            FirebaseDatabase.getInstance().getReference().child("Foods").child(dataSnapshot1.getKey()).child("favorite_icon").setValue("https://iili.io/HPUQz3x.png");

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

        });*/}
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_cart,parent,false);
        return new myviewholder(view);
    }
    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView item_image,delete,edit;
        TextView item_name,pre,item_price,cal,description;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            item_image=itemView.findViewById(R.id.item_img);
          //  icon_facorite=itemView.findViewById(R.id.icon_favorite);
            item_name=itemView.findViewById(R.id.item_name);
            pre=itemView.findViewById(R.id.restaurantCart_time);
            item_price=itemView.findViewById(R.id.restaurantCart_price);
            cal=itemView.findViewById(R.id.restaurantCart_tv_Cal);
            description=itemView.findViewById(R.id.description_item);
           edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);

        }
    }

}