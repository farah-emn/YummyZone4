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
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Category_adapter;
import com.example.yummyzone.adapter.Menu_Adapter;
import com.example.yummyzone.adapter.restaurantAdapter;
import com.example.yummyzone.classes.Category_tab;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.classes.restaurant;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.play.integrity.internal.a;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class searchFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    EditText search;
    RecyclerView recview;
    Menu_Adapter adapter1;
    DatabaseReference mbase1;
    public searchFragment() {}

    public static searchFragment newInstance(String param1, String param2) {
        searchFragment fragment = new searchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);}}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        search = view.findViewById(R.id.searchScreen_et_search);
        recview = view.findViewById(R.id.sh);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mbase1 = FirebaseDatabase.getInstance().getReference("Foods");
                recview.setLayoutManager(new GridLayoutManager(getContext(), 2));
                String s = search.getText().toString();
                FirebaseRecyclerOptions<Menu_tab> options3= new FirebaseRecyclerOptions.Builder<Menu_tab>().setQuery(mbase1.orderByChild("item_name").startAt(s).endAt(s + "\uf8ff"), Menu_tab.class).build();
                recview.setLayoutManager(new GridLayoutManager(getContext(), 2));
                adapter1 = new Menu_Adapter(options3);
                recview.setAdapter(adapter1);
                adapter1.startListening();
                if (search.getText().toString().trim().length() == 0) {
                    recview.setAdapter(null);
                    adapter1.notifyDataSetChanged();}}
             @Override
             public void afterTextChanged(Editable editable) {}});

        return view;
    }
}