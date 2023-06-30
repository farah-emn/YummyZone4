package com.example.yummyzone.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Menu_Adapter;
import com.example.yummyzone.classes.Category_tab;
import com.example.yummyzone.classes.Menu_tab;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MenuFragment extends Fragment {
    RecyclerView RecyclerViewCategory, RecyclerViewFoodList,RecyclerViewFoodList1;

    TextView item_name, item_price, pre_time, category_name;

    ImageView item_img, icon_favorite, tab_image,image_back;
    private RecyclerView recyclerView;

    DatabaseReference mbase;
    Menu_Adapter adapter1;
    DatabaseReference mbase1;
   String post_key1="",post_key3="";
    public MenuFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        mbase = FirebaseDatabase.getInstance().getReference("Category");
        recyclerView = view.findViewById(R.id.RecyclerViewCategory);
        RecyclerViewFoodList = view.findViewById(R.id.RecyclerViewFoodList);
        image_back=view.findViewById(R.id.image_back);
        Bundle args = getArguments();
        Bundle args1 = getArguments();
        post_key1 = args.getString("cate");

            recyclerView.setLayoutManager(
                    new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            FirebaseRecyclerOptions<Category_tab> options
                    = new FirebaseRecyclerOptions.Builder<Category_tab>()
                    .setQuery(mbase, Category_tab.class)
                    .build();
            //recyclerView.setAdapter(adapter);
          //  adapter.startListening();

            post_key3 = args1.getString("cate4");
            mbase1 = FirebaseDatabase.getInstance().getReference("Foods");
            RecyclerViewFoodList.setLayoutManager(
                    new GridLayoutManager(getContext(), 2));
            FirebaseRecyclerOptions<Menu_tab> options3 = new FirebaseRecyclerOptions.Builder<Menu_tab>()
                    .setQuery(mbase1.orderByChild("restaurantid"), Menu_tab.class)
                    .build();
            adapter1 = new Menu_Adapter(options3);
            RecyclerViewFoodList.setAdapter(adapter1);
            adapter1.startListening();

        return view;}}


