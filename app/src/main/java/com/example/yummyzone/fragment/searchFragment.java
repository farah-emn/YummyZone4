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
import com.example.yummyzone.adapter.Category_Adapter;
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
    EditText search;
    RecyclerView recyclerViewSearch;
    Menu_Adapter menu_adapter;

    public searchFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        search = view.findViewById(R.id.searchScreen_et_search);
        recyclerViewSearch = view.findViewById(R.id.sh);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
                String s = search.getText().toString();
                FirebaseRecyclerOptions<Menu_tab> options3= new FirebaseRecyclerOptions.Builder<Menu_tab>().setQuery(FirebaseDatabase.getInstance().getReference("Foods").orderByChild("item_name").startAt(s).endAt(s + "\uf8ff"), Menu_tab.class).build();
                recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
                menu_adapter = new Menu_Adapter(options3);
                recyclerViewSearch.setAdapter(menu_adapter);
                menu_adapter.startListening();
                if (search.getText().toString().trim().length() == 0) {
                    recyclerViewSearch.setAdapter(null);
                    menu_adapter.notifyDataSetChanged();}}
             @Override
             public void afterTextChanged(Editable editable) {}});

        return view;
    }
}