package com.example.yummyzone.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.Cart;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class itemInfoFragment extends Fragment {
    TextView tv_restaurantName;
    TextView tv_itemName;
    TextView tv_itemNumber;
    TextView tv_description;
    TextView tv_time;
    TextView tv_price;
    TextView tv_cal;
    TextView tv_qty;
    TextView price_total;
    ImageView iv_like;
    ImageView iv_back;
    ImageView iv_item;
    ImageView iv_minus;
    ImageView iv_plus;
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    String username;
    Button bt_add;
    public String name,image,id,price,restaurant,description,prepration_time,calories;

    int count=0;
    public itemInfoFragment(String name, String image, String id, String price, String restaurant, String description,String calories,String prepration_time) {
        this.name=name;
        this.image=image;
        this.id=id;
        this.price=price;
        this.restaurant=restaurant;
        this.description=description;
        this.calories=calories;
        this.prepration_time=prepration_time;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_item, container, false);
        tv_restaurantName = view.findViewById(R.id.itemInfo_tv_restaurantNme);
        tv_itemName = view.findViewById(R.id.itemInfo_tv_itemName);
        tv_itemNumber = view.findViewById(R.id.itemInfo_tv_itemNumber);
        tv_description = view.findViewById(R.id.itemInfo_tv_description);
        tv_time = view.findViewById(R.id.itemInfo_tv_time);
        tv_price = view.findViewById(R.id.itemInfo_tv_price);
        tv_cal = view.findViewById(R.id.itemInfo_tv_cal);
        iv_like = view.findViewById(R.id.itemInfo_image_like);
        iv_back = view.findViewById(R.id.itemInfo_image_back);
        iv_item = view.findViewById(R.id.itemInfo_image_item);
        iv_minus = view.findViewById(R.id.itemInfo_image_minus);
        iv_plus = view.findViewById(R.id.itemInfo_image_plus);
        bt_add = view.findViewById(R.id.itemInfo_bt_add);
        tv_restaurantName.setText(restaurant);
        tv_description.setText(description);
        tv_qty=view.findViewById(R.id.itemInfo_tv_itemNumber);
        price_total=view.findViewById(R.id.price);
        tv_itemName.setText(name);
        tv_time.setText(prepration_time+" "+"Min");
        tv_cal.setText(calories);
        tv_price.setText(price +" "+"SR");
        price_total.setText(price);
        Glide.with(this).load(image).into(iv_item);
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");



       iv_plus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        count++;
        tv_qty.setText(""+count);
        String price10 = String.valueOf( Double.parseDouble(String.valueOf( Double.parseDouble(price)*count)));
        price_total.setText(price10);}});

      iv_minus.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          if(count!=1){
          count--;
              String price4 =String.valueOf( Double.parseDouble ((String) price_total.getText())- Double.parseDouble( price));
              price_total.setText(price4);
              tv_qty.setText(""+count);}}});

     bt_add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             userR.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot1) {
                     for (DataSnapshot keyId : snapshot1.getChildren()) {
                         if (keyId.child("email").getValue().equals(user.getEmail())) {
                             username = keyId.child("username").getValue(String.class);
                             FirebaseDatabase.getInstance().getReference().child("Cart").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                     if (snapshot.getValue()==null) {
                                     Toast.makeText(getContext(), "Successfully added to cart1"+(snapshot.getValue()==null), Toast.LENGTH_SHORT).show();
                                     String item_image = image;
                                     String item_name = name;
                                     String qty = (String) tv_qty.getText();
                                     String item_price = (String) price_total.getText();
                                     String restaurant_id = restaurant;
                                     String total_price = "";
                                     Cart UserCart = new Cart(item_image, item_name, qty, item_price, restaurant_id, total_price);
                                     FirebaseDatabase.getInstance().getReference().child("Cart").child(username).child(item_name).setValue(UserCart);
                                } else if (snapshot.getValue()!=null) {
                                          FirebaseDatabase.getInstance().getReference().child("Cart").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot snapshot2) {
                                                  for (DataSnapshot dataSnapshot : snapshot2.getChildren()) {
                                                      String keyresturant = dataSnapshot.getKey();
                                                      String restaurant_id = (String) snapshot2.child(keyresturant).child("id").getValue();
                                                      if (restaurant_id.equals(restaurant)) {
                                                          Toast.makeText(getContext(), "Successfully added to cart", Toast.LENGTH_SHORT).show();
                                                          String item_image = image;
                                                          String item_name = name;
                                                          String qty = (String) tv_qty.getText();
                                                          String item_price = (String) price_total.getText();
                                                          String id = restaurant;
                                                          String total_price = "";
                                                          username = keyId.child("username").getValue(String.class);
                                                          Cart UserCart = new Cart(item_image, item_name, qty, item_price, id, total_price);
                                                          FirebaseDatabase.getInstance().getReference().child("Cart").child(username).child(item_name).setValue(UserCart);

                                                      }
                                                      else {
                                                          Toast.makeText(getContext(), " can not add from a different restaurant", Toast.LENGTH_SHORT).show();
                                                          break;}}}
                                              @Override
                                              public void onCancelled(@NonNull DatabaseError error) {}});}}
                                 @Override
                                 public void onCancelled(@NonNull DatabaseError error) {}});}}}
                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {}});}});


     iv_back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Fragment fragment=new MenuFragment();
             Bundle bundle=new Bundle();
             bundle.putString("restaurant_id", restaurant);
             fragment.setArguments(bundle);
             AppCompatActivity activity = (AppCompatActivity) view.getContext();
             activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
         }



     });

  return view;
    }
}
