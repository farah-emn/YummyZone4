package com.example.yummyzone.fragment;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Category_adaptermenu;
import com.example.yummyzone.adapter.cartItemAdapter;
import com.example.yummyzone.classes.Cart;
import com.example.yummyzone.classes.items;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class cartFragment extends Fragment {
    RecyclerView RecyclerViewCategory, RecyclerViewFoodList, RecyclerViewFoodList1;
    TextView item_name, item_price, pre_time, category_name;
    ImageView item_img, icon_favorite, tab_image;
    BottomNavigationView nav;
    String postkey = "";
    private RecyclerView recyclerView;
    Category_adaptermenu adapter, adapter2;
    DatabaseReference mbase;
    RecyclerView recview1;
    cartItemAdapter adapter1, adapter3;
    DatabaseReference mbase1, mbase2;
    FirebaseDatabase firebaseDatabase1, firebaseDatabase2;
    String cate = "", cate2 = "";
    String post_key1 = "", post_key3 = "";
    RecyclerView rv;
    Button b;
    TextView tv;
    TextView cart_tv_price;
    String name = "";
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    ArrayList<String> list;
    double sum = 0;

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
        rv = view.findViewById(R.id.cart_rv);
        b = view.findViewById(R.id.cart_bt);
        tv = view.findViewById(R.id.cart_tv_price);
        tv.setText("0");
        cart_tv_price = view.findViewById(R.id.cart_tv_price);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        list =new ArrayList<String>();
        Bundle arguments = getArguments();
        tv.setText(String.valueOf( cartItemAdapter.sum));


        userR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyId : snapshot.getChildren()) {
                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                        String username = keyId.child("username").getValue(String.class);
                        mbase1 = FirebaseDatabase.getInstance().getReference("Cart").child(keyId.child("username").getValue(String.class));
                        rv.setLayoutManager(
                                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        FirebaseRecyclerOptions<Cart> options3 = new FirebaseRecyclerOptions.Builder<Cart>()
                                .setQuery(mbase1, Cart.class)
                                .build();
                        adapter1 = new cartItemAdapter(options3);
                        // cart_tv_price.setText();
                        rv.setAdapter(adapter1);
                        adapter1.startListening();
                        FirebaseDatabase.getInstance().getReference().child("Cart").child(username).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                list.clear();
                                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                                    String M=dataSnapshot.child("tot").getValue(String.class);
                                    list.add(M);
                                    tv.setText(list.get(list.size()-1));
                                }if(list.isEmpty()){
                                    tv.setText("0.0");
                                }else { tv.setText(list.get(list.size()-1));}



                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive).addBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_Gray)).addActionIcon(R.drawable.delete_icon).create().decorate();
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(item);
        itemTouchHelper.attachToRecyclerView(rv);


        return view;
    }

 void b(){



 }


 }




