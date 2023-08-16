package com.example.yummyzone.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.restaurant_menu_Adapter;
import com.example.yummyzone.classes.Menu_tab;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class restaurant_searchFragment extends Fragment {
    EditText search;
    RecyclerView recyclerViewSearch;
    restaurant_menu_Adapter menu_adapter;
    String res;
    public restaurant_searchFragment(String res) {
        this.res=res;
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_restaurant_search, container, false);
        search = view.findViewById(R.id.searchScreen_et_search);
        recyclerViewSearch = view.findViewById(R.id.sh);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
                String s = search.getText().toString();
                //String ss=s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                FirebaseRecyclerOptions<Menu_tab> options3= new FirebaseRecyclerOptions.Builder<Menu_tab>()
                        .setQuery(FirebaseDatabase.getInstance()
                                        .getReference("Foods").orderByKey().startAt(res+"_"+s).endAt(res+"_"+s+"\uf8ff")
                                , Menu_tab.class).build();
                recyclerViewSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
                menu_adapter = new restaurant_menu_Adapter(options3);
                //  Toast.makeText(view.getContext(), "ok+"+ss, Toast.LENGTH_SHORT).show();
                recyclerViewSearch.setAdapter(menu_adapter);
                menu_adapter.startListening();
                if (search.getText().toString().trim().length() == 0)
                {
                    recyclerViewSearch.setAdapter(null);
                    menu_adapter.notifyDataSetChanged();
                }}
            @Override
            public void afterTextChanged(Editable editable) {}});


        return view;}
}