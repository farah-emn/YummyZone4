package com.example.yummyzone.fragment;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Category_Adapter;
import com.example.yummyzone.adapter.restaurantAdapter;
import com.example.yummyzone.classes.Category_tab;
import com.example.yummyzone.classes.restaurant;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class homeFragment extends Fragment {
    RecyclerView recyclerView_restaurants,recyclerView_category;
    Category_Adapter category_adapter;
    restaurantAdapter restaurantadapter;
    DatabaseReference databaseReference;
    String restaurant_id;
    EditText search;
    ProgressBar p;

    public homeFragment(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView_category = (RecyclerView) view.findViewById(R.id.homeScreen_rv_tabs);
        search = view.findViewById(R.id.homeScreen_et_search);
        p=view.findViewById(R.id.home_pro);
        p.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.dark_orange), PorterDuff.Mode.SRC_IN);

        FirebaseDatabase.getInstance().getReference().child("restaurant").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                p.setVisibility(View.GONE);
                int i=1;

                for (DataSnapshot dataSnapshot1:snapshot.getChildren()

                ){if (dataSnapshot1.getChildrenCount() > 0) {


                    p.setVisibility(View.INVISIBLE);
                }}
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView_category.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<Category_tab> categoryFirebaseRecyclerOptions =new FirebaseRecyclerOptions.Builder<Category_tab>().setQuery(FirebaseDatabase.getInstance().getReference().child("Category"), Category_tab.class).build();
        category_adapter = new Category_Adapter(categoryFirebaseRecyclerOptions);
        category_adapter.startListening();
        recyclerView_category.setAdapter(category_adapter);

        recyclerView_restaurants = (RecyclerView) view.findViewById(R.id.homeScreen_rv_restaurant);
        databaseReference = FirebaseDatabase.getInstance().getReference("restaurant");
        recyclerView_restaurants.setLayoutManager(new GridLayoutManager(getContext(), 2));
        FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<restaurant>().setQuery(databaseReference, restaurant.class).build();
        restaurantadapter= new restaurantAdapter(restaurantFirebaseRecyclerOptions);
        recyclerView_restaurants.setAdapter(restaurantadapter);
        restaurantadapter.startListening();



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = search.getText().toString();
                FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<restaurant>().setQuery(FirebaseDatabase.getInstance().getReference("restaurant").orderByChild("restaurant_name") .startAt(s).endAt(s + "\uf8ff"), restaurant.class).build();
                restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions);
                recyclerView_restaurants.setAdapter(restaurantadapter);
                restaurantadapter.startListening();
            }
        });

        if (restaurant_id != "") {
            FirebaseDatabase.getInstance().getReference().child("restaurant").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        if (restaurant_id.equals(dataSnapshot.child("categoryCoffee_id").getValue())) {

                            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>()
                                    .setQuery(databaseReference.orderByChild("categoryCoffee_id").equalTo(restaurant_id), restaurant.class).build();
                            restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
                            recyclerView_restaurants.setAdapter(restaurantadapter);
                            restaurantadapter.startListening();
                        } else if (restaurant_id.equals(dataSnapshot.child("categoryBurgers_id").getValue())) {
                            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>()
                                    .setQuery(databaseReference.orderByChild("categoryBurgers_id").equalTo(restaurant_id), restaurant.class).build();
                            restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
                            recyclerView_restaurants.setAdapter(restaurantadapter);
                            restaurantadapter.startListening();

                        } else if (restaurant_id.equals(dataSnapshot.child("categorySandwiche_id").getValue())) {
                            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>()
                                    .setQuery(databaseReference.orderByChild("categorySandwiche_id").equalTo(restaurant_id), restaurant.class).build();
                            restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
                            recyclerView_restaurants.setAdapter(restaurantadapter);
                            restaurantadapter.startListening();

                        } else if (restaurant_id.equals(dataSnapshot.child("categoryDesserts_id").getValue())) {
                            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>()
                                    .setQuery(databaseReference.orderByChild("categoryDesserts_id").equalTo(restaurant_id), restaurant.class).build();
                            restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
                            recyclerView_restaurants.setAdapter(restaurantadapter);
                            restaurantadapter.startListening();
                        } else if
                        (restaurant_id.equals(dataSnapshot.child("categoryBeverages_id").getValue())) {
                            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>()
                                    .setQuery(databaseReference.orderByChild("categoryBeverages_id").equalTo(restaurant_id), restaurant.class).build();
                            restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
                            recyclerView_restaurants.setAdapter(restaurantadapter);
                            restaurantadapter.startListening();
                        } else if (restaurant_id.equals(dataSnapshot.child("categoryPizza_id").getValue())) {
                            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>()
                                    .setQuery(databaseReference.orderByChild("categoryPizza_id").equalTo(restaurant_id), restaurant.class).build();
                            restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
                            recyclerView_restaurants.setAdapter(restaurantadapter);
                            restaurantadapter.startListening();
                        }
                        else if (restaurant_id.equals(dataSnapshot.child("categorySeaFood_id").getValue())) {
                            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>()
                                    .setQuery(databaseReference.orderByChild("categorySeaFood_id").equalTo(restaurant_id), restaurant.class).build();
                            restaurantadapter = new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
                            recyclerView_restaurants.setAdapter(restaurantadapter);
                            restaurantadapter.startListening();
                        }



                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }else {
            recyclerView_restaurants = (RecyclerView) view.findViewById(R.id.homeScreen_rv_restaurant);
            databaseReference = FirebaseDatabase.getInstance().getReference("restaurant");
            recyclerView_restaurants.setLayoutManager(new GridLayoutManager(getContext(), 2));
            FirebaseRecyclerOptions<restaurant> restaurantFirebaseRecyclerOptions1 = new FirebaseRecyclerOptions.Builder<restaurant>().setQuery(databaseReference, restaurant.class).build();
            restaurantadapter= new restaurantAdapter(restaurantFirebaseRecyclerOptions1);
            recyclerView_restaurants.setAdapter(restaurantadapter);
            restaurantadapter.startListening();}


        return view;
    }

}