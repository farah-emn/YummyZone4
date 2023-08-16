package com.example.yummyzone.fragment;

import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.R;
import com.example.yummyzone.adapter.restaurant_menu_Adapter;
import com.example.yummyzone.classes.Menu_tab;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class restaurant_menuFragment extends Fragment {
    RecyclerView RecyclerViewFoodList;
    ImageView image_back;
    restaurant_menu_Adapter restaurant_menu_adapter;
    DatabaseReference databaseReference;
    TextView restaurant_name;
    ProgressBar progressBar;
    DatabaseReference restR, rootR;
    FirebaseAuth Auth;
    FirebaseUser user;

    public String res;

    public restaurant_menuFragment(String res) {
        this.res=res;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        restR= rootR.child("restaurant");
        View view= inflater.inflate(R.layout.fragment_restaurant_menu, container, false);
        RecyclerViewFoodList = view.findViewById(R.id.restaurant_Menu_rv);
        restaurant_name=view.findViewById(R.id.restaurant_name);
        progressBar=view.findViewById(R.id.resprograss);
        restaurant_name.setText(res);
        databaseReference = FirebaseDatabase.getInstance().getReference("Foods");
        RecyclerViewFoodList.setLayoutManager(new GridLayoutManager(getContext(),2));
        FirebaseRecyclerOptions<Menu_tab> MenuFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Menu_tab>().setQuery(databaseReference.orderByChild("restaurantid").equalTo(res), Menu_tab.class).build();
        restaurant_menu_adapter = new restaurant_menu_Adapter(MenuFirebaseRecyclerOptions);
        RecyclerViewFoodList.setAdapter(restaurant_menu_adapter);
        restaurant_menu_adapter.startListening();
        progressBar.getIndeterminateDrawable().setColorFilter(getResources()
                .getColor(R.color.dark_orange), PorterDuff.Mode.SRC_IN);

        FirebaseDatabase.getInstance().getReference().child("Foods").child("restaurantid").equalTo(res).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBar.setVisibility(View.GONE);
                int i=1;

                for (DataSnapshot dataSnapshot1:snapshot.getChildren()

                ){if (dataSnapshot1.getChildrenCount() > 0) {


                    progressBar.setVisibility(View.INVISIBLE);
                }}
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });













        //Bundle bundle = this.getArguments();
        // String restaurantName= bundle.getString("restaurantName");

        return view;





    }
}