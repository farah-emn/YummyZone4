package com.example.yummyzone.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.Category_adapter;
import com.example.yummyzone.adapter.restaurantAdapter;
import com.example.yummyzone.classes.Category_tab;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.classes.restaurant;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class homeFragment extends Fragment {


    private String mParam1;
    private String mParam2;
    EditText search;
    RecyclerView recview;
    Category_adapter adapter;

    DatabaseReference mbase;
    RecyclerView recview1,recview2;
   restaurantAdapter adapter1, adapter2;
    DatabaseReference mbase1,mbase2;
    FirebaseDatabase firebaseDatabase1,firebaseDatabase2;
    String cate="";
    String post_key;
    Button button;

    public homeFragment(String post_key) {
        this.post_key=post_key;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recview=(RecyclerView)view.findViewById(R.id.homeScreen_rv_tabs);
       // button=view.findViewById(R.id.button);
        recview.setLayoutManager(
                new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));
        FirebaseRecyclerOptions<Category_tab> options =
                new FirebaseRecyclerOptions.Builder<Category_tab>().setQuery(FirebaseDatabase.getInstance().getReference().child("Category"),Category_tab.class).build();
        adapter=new Category_adapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
        recview2 = (RecyclerView) view.findViewById(R.id.homeScreen_rv_restaurant);
        firebaseDatabase2 = FirebaseDatabase.getInstance();
        mbase2=firebaseDatabase2.getReference("restaurant");
        recview2.setLayoutManager(
                new GridLayoutManager(getContext(),2)  );
        FirebaseRecyclerOptions<restaurant> options3
                = new FirebaseRecyclerOptions.Builder<restaurant>()
                .setQuery(mbase2, restaurant.class)
                .build();   adapter2= new restaurantAdapter(
                options3);
        recview2.setAdapter(adapter2);
        adapter2.startListening();

        search=view.findViewById(R.id.homeScreen_et_search);

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
             //     Toast.makeText(getActivity(), "jjj", Toast.LENGTH_SHORT).show();
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
   if (post_key!="") {
    recview1 = (RecyclerView) view.findViewById(R.id.homeScreen_rv_restaurant);
    firebaseDatabase1 = FirebaseDatabase.getInstance();
    mbase1=firebaseDatabase1.getReference("restaurant");
    recview1.setLayoutManager(
            new GridLayoutManager(getContext(),2));
    FirebaseRecyclerOptions<restaurant> options2
            = new FirebaseRecyclerOptions.Builder<restaurant>()
            .setQuery(mbase1.orderByChild("cateid").equalTo(post_key), restaurant.class)
            .build();   adapter1 = new restaurantAdapter(
            options2);
    recview1.setAdapter(adapter1);
    adapter1.startListening();
}
   return view; }


   // public FragmentTransaction addToBackStack(Object o) {
  //  return null;
    //}
}


