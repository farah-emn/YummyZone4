package com.example.yummyzone.fragment;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.R;
import com.example.yummyzone.activites.MapActivity;
import com.example.yummyzone.adapter.cartItemAdapter;
import com.example.yummyzone.classes.Cart;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;


public class cartFragment extends Fragment {
    cartItemAdapter cartadapter;
    RecyclerView recyclerViewCart;
    Button b;
    TextView tv;
    TextView cart_tv_price;
    String name ;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR, cartR;
    ArrayList<String> list;
    double sum = 0;
    String username;
    String delivery_fee, restaurantName;
    ProgressBar p;

    public cartFragment() {
    }
    public cartFragment(String name) {
        this.name = name;
    }

    public cartFragment(double sum) {
        this.sum = sum;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerViewCart = view.findViewById(R.id.cart_rv);
        b = view.findViewById(R.id.cart_bt);
        tv = view.findViewById(R.id.cart_tv_price);
        tv.setText("0");
        cart_tv_price = view.findViewById(R.id.cart_tv_price);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        cartR = rootR.child("Cart");
        list =new ArrayList<String>();
        p=view.findViewById(R.id.cart_pro);
        p.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.dark_orange), PorterDuff.Mode.SRC_IN);


        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                        username = keyId.child("username").getValue(String.class);
                        recyclerViewCart.setLayoutManager( new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        FirebaseRecyclerOptions<Cart> cartFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Cart>() .setQuery( FirebaseDatabase.getInstance().getReference("Cart").child(keyId.child("username").getValue(String.class)), Cart.class).build();
                        cartadapter = new cartItemAdapter(cartFirebaseRecyclerOptions);
                        recyclerViewCart.setAdapter(cartadapter);
                        cartadapter.startListening();
                        FirebaseDatabase.getInstance().getReference().child("Cart").child(username).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                p.setVisibility(View.GONE);

                                list.clear();
                                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                    if (dataSnapshot.getChildrenCount() > 0) {
                                        p.setVisibility(View.INVISIBLE);
                                    } else {
                                        p.setVisibility(View.INVISIBLE);

                                    }

                                    FirebaseDatabase.getInstance().getReference().child("restaurant").addListenerForSingleValueEvent(new ValueEventListener() {

                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            for (DataSnapshot dataSnapshot1:snapshot.getChildren()){
                                                if(dataSnapshot.child("restaurantid").getValue().equals(dataSnapshot1.getKey())) {
                                                    delivery_fee = (String) dataSnapshot1.child("delivery_fee").getValue();
                                                    restaurantName = (String) dataSnapshot1.child("restaurant_name").getValue();

                                                }}}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }});
                                    String M=dataSnapshot.child("total_price").getValue(String.class);
                                    list.add(M);
                                    tv.setText(list.get(list.size()-1));
                                }if(list.isEmpty()){
                                    tv.setText("0.0");
                                }else { tv.setText(list.get(list.size()-1));}}
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {}});}}}
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(),MapActivity.class);
                intent.putExtra("total", tv.getText().toString());
                intent.putExtra("delivery_fee", delivery_fee);
                intent.putExtra("restaurant_name", restaurantName);
                startActivity(intent);
            }
        });
        ItemTouchHelper.SimpleCallback item = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                cartItemAdapter.myviewholder n = (cartItemAdapter.myviewholder) viewHolder;
                n.delete();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive).addActionIcon(R.drawable.delete_icon).create().decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(recyclerViewCart);





        return view;
    }



}