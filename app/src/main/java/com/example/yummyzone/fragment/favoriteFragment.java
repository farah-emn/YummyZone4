package com.example.yummyzone.fragment;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Menu_Adapter;
import com.example.yummyzone.classes.Menu_tab;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    ProgressBar p;

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

        p=view.findViewById(R.id.favorite_pro);
        p.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.dark_orange), PorterDuff.Mode.SRC_IN);
        recyclerViewFavorite = view.findViewById(R.id.favorite_rv);
        ArrayList<Menu_tab> tt =new ArrayList<>();
        RecyclerView.LayoutManager lm2 =new GridLayoutManager(this.getContext(),2);
        recyclerViewFavorite.setLayoutManager(lm2);
        userR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {   for (DataSnapshot keyId : snapshot.getChildren()) {
                if (keyId.child("email").getValue().equals(user.getEmail())) {
                    String username = keyId.child("username").getValue(String.class);

                    FirebaseDatabase.getInstance().getReference().child("favorite").child(username).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            p.setVisibility(View.GONE);
                            int i=1;

                            for (DataSnapshot dataSnapshot1:snapshot.getChildren()

                            ){if (dataSnapshot1.getChildrenCount() > 0) {


                                p.setVisibility(View.INVISIBLE);
                            }
                            else {
                                p.setVisibility(View.INVISIBLE);
                            }}
                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

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