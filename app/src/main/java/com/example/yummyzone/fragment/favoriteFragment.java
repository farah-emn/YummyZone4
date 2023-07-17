package com.example.yummyzone.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Menu_Adapter;
import com.example.yummyzone.adapter.cartItemAdapter;
import com.example.yummyzone.classes.Cart;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.classes.cartItem;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class favoriteFragment extends Fragment {
    RecyclerView recyclerViewFavorite;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    Menu_Adapter menu_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_favorite, container, false);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");

        recyclerViewFavorite = view.findViewById(R.id.favorite_rv);
        ArrayList<Menu_tab> tt =new ArrayList<>();
        RecyclerView.LayoutManager lm2 =new GridLayoutManager(this.getContext(),2);
        recyclerViewFavorite.setLayoutManager(lm2);
        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {   for (DataSnapshot keyId : snapshot.getChildren()) {
                if (keyId.child("email").getValue().equals(user.getEmail())) {
                    String username = keyId.child("username").getValue(String.class);
                    recyclerViewFavorite.setLayoutManager(
                            new GridLayoutManager(getContext(), 2));
                            FirebaseRecyclerOptions<Menu_tab> favoriteFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Menu_tab>()
                            .setQuery(FirebaseDatabase.getInstance().getReference("Favourite").child(keyId.child("username").getValue(String.class)), Menu_tab.class)
                            .build();
                    menu_adapter = new Menu_Adapter(favoriteFirebaseRecyclerOptions);
                    recyclerViewFavorite.setAdapter(menu_adapter);
                    menu_adapter.startListening();}}}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;


    }
}