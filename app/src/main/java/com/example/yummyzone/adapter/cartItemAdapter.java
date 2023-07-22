package com.example.yummyzone.adapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.yummyzone.classes.Cart;
import com.example.yummyzone.R;
import com.example.yummyzone.fragment.cartFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class cartItemAdapter extends FirebaseRecyclerAdapter<Cart,cartItemAdapter.myviewholder>
{   FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    String username;
    double sum=0;
    ArrayList<Cart> list;
    public cartItemAdapter(@NonNull FirebaseRecyclerOptions<Cart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final Cart model) {
        holder.name.setText((CharSequence) model.getItem_name());
        holder.price.setText((CharSequence) model.getItem_price());
        holder.number.setText((CharSequence) model.getQty());
        Picasso.get().load(model.getItem_image()) .into(holder.item_image);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        list = new ArrayList<Cart>();


        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId : snapshot.getChildren()){
                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                        username = keyId.child("username").getValue(String.class);
                        holder.tv_minus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int qty = Integer.parseInt((String) holder.number.getText());
                                double price_item =  Double.parseDouble((String) holder.price.getText());
                                double pricec = price_item / qty;
                                if (qty != 1) {
                                    qty--;
                                    holder.number.setText(String.valueOf(qty));
                                    FirebaseDatabase.getInstance().getReference("Cart").child(username)
                                            .child(getRef(position).getKey()).child("qty").setValue(String.valueOf(String.valueOf(qty)));
                                    double price = price_item - pricec;
                                    holder.price.setText(String.valueOf(price));
                                    FirebaseDatabase.getInstance().getReference("Cart").child(username)
                                            .child(getRef(position).getKey()).child("item_price").setValue(String.valueOf(price));

                                }}});
                        holder.tv_plus.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int qty = Integer.parseInt((String) holder.number.getText());
                                double price_item =  Double.parseDouble((String) holder.price.getText());
                                double pricec = price_item / qty;
                                qty++;
                                holder.number.setText(String.valueOf(qty));
                                FirebaseDatabase.getInstance().getReference("Cart")
                                        .child(username).child(getRef(position).getKey()).child("qty").setValue(String.valueOf(String.valueOf(qty)));
                                double price = price_item + pricec;
                                holder.price.setText(String.valueOf(price));
                                FirebaseDatabase.getInstance().getReference("Cart")
                                        .child(username).child(getRef(position).getKey()).child("item_price").setValue(String.valueOf(price));

                            }});}}}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}});

        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId : snapshot.getChildren()){
                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                        String username= keyId.child("username").getValue(String.class);
                        FirebaseDatabase.getInstance().getReference().child("Cart").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) { int items=0;
                                list.clear();
                                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                    String keyresturant = dataSnapshot.getKey();
                                        Cart cart = dataSnapshot.getValue(Cart.class);
                                        list.add(cart);
                                         sum = 0;
                                }
                                for (Cart b :list) {
                                           sum += Double.parseDouble(b.getItem_price());
                                           FirebaseDatabase.getInstance().getReference().child("Cart").child(username).child(b.getRestaurantid()+"_"+b.getItem_name()).child("total_price").setValue(String.valueOf(sum));}}
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}});}}}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}});

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new myviewholder(view1);
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        ImageView item_image;
        RecyclerView rv;
        ImageView tv_plus;
        ImageView tv_minus;
        TextView name;
        TextView number;
        TextView price;
        TextView tv;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            item_image = itemView.findViewById(R.id.itemInfo_iv_itemImage);
            name = itemView.findViewById(R.id.itemInfo_tv_itemName);
            number = itemView.findViewById(R.id.itemInfo_tv_itemNumber);
            price = itemView.findViewById(R.id.itemInfo_tv_price);
            tv_plus = itemView.findViewById(R.id.itemInfo_iv_plus);
            tv_minus = itemView.findViewById(R.id.itemInfo_iv_minus);
            rv=itemView.findViewById(R.id.cart_rv);
            tv = itemView.findViewById(R.id.cart_tv_price);
        }
         public void delete() {
             Auth = FirebaseAuth.getInstance();
             user = Auth.getCurrentUser();
             rootR = FirebaseDatabase.getInstance().getReference();
             userR = rootR.child("user");

             userR.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                     for (DataSnapshot keyId : snapshot.getChildren()) {
                         if (keyId.child("email").getValue().equals(user.  getEmail())) {
                             username = keyId.child("username").getValue(String.class);
                             FirebaseDatabase.getInstance()
                                     .getReference()
                                     .child("Cart").child(username).child(getRef(getPosition()).getKey()).removeValue();}}}

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {
                 }
             });

         }}

}
