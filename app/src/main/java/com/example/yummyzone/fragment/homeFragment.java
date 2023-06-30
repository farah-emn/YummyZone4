package com.example.yummyzone.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Category_adapter;
import com.example.yummyzone.adapter.restaurantAdapter;
import com.example.yummyzone.classes.Category_tab;
import com.example.yummyzone.classes.restaurant;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homeFragment extends Fragment {
    EditText search;
    RecyclerView recview;
    Category_adapter adapter;
    RecyclerView recview1, recview2;
    restaurantAdapter adapter1, adapter2;
    DatabaseReference mbase1, mbase2;
    FirebaseDatabase firebaseDatabase1, firebaseDatabase2;
    String post_key1 = "";

    public homeFragment(String post_key1) {
        this.post_key1 = post_key1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recview = (RecyclerView) view.findViewById(R.id.homeScreen_rv_tabs);
        search = view.findViewById(R.id.homeScreen_et_search);

        recview.setLayoutManager(
                new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<Category_tab> options =
                new FirebaseRecyclerOptions.Builder<Category_tab>().setQuery(FirebaseDatabase.getInstance().getReference().child("Category"), Category_tab.class).build();
        adapter = new Category_adapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
        recview2 = (RecyclerView) view.findViewById(R.id.homeScreen_rv_restaurant);
        firebaseDatabase2 = FirebaseDatabase.getInstance();
        mbase2 = firebaseDatabase2.getReference("restaurant");
        recview2.setLayoutManager(new GridLayoutManager(getContext(), 2));
        FirebaseRecyclerOptions<restaurant> options3 = new FirebaseRecyclerOptions.Builder<restaurant>()
                .setQuery(mbase2, restaurant.class).build();
        adapter2 = new restaurantAdapter(options3);
        recview2.setAdapter(adapter2);
        adapter2.startListening();


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
                FirebaseRecyclerOptions<restaurant> options4
                        = new FirebaseRecyclerOptions.Builder<restaurant>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("restaurant")
                                .orderByChild("name")
                                .startAt(s).endAt(s + "\uf8ff"), restaurant.class)
                        .build();
                adapter2 = new restaurantAdapter(
                        options4);
                recview2.setAdapter(adapter2);
                adapter2.startListening();
            }
        });

        if (post_key1 != "" && post_key1 != "0") {
            recview1 = (RecyclerView) view.findViewById(R.id.homeScreen_rv_restaurant);
            firebaseDatabase1 = FirebaseDatabase.getInstance();
            mbase1 = firebaseDatabase1.getReference("restaurant");
            recview1.setLayoutManager(
                    new GridLayoutManager(getContext(), 2));
            FirebaseRecyclerOptions<restaurant> options1
                    = new FirebaseRecyclerOptions.Builder<restaurant>()
                    .setQuery(mbase1.orderByChild("cateid").equalTo(post_key1), restaurant.class)
                    .build();
            adapter1 = new restaurantAdapter(
                    options1);
            recview1.setAdapter(adapter1);
            adapter1.startListening();
        }

        if (post_key1.equals("0")) {
            recview1 = (RecyclerView) view.findViewById(R.id.homeScreen_rv_restaurant);
            firebaseDatabase1 = FirebaseDatabase.getInstance();
            mbase1 = firebaseDatabase1.getReference("restaurant");
            recview1.setLayoutManager(
                    new GridLayoutManager(getContext(), 2));
            FirebaseRecyclerOptions<restaurant> options1
                    = new FirebaseRecyclerOptions.Builder<restaurant>()
                    .setQuery(mbase1, restaurant.class)
                    .build();
            adapter1 = new restaurantAdapter(
                    options1);
            recview1.setAdapter(adapter1);
            adapter1.startListening();
        }
        return view;
    }

}


