package com.example.yummyzone.adapter;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yummyzone.classes.Menu_tab;
import com.example.yummyzone.R;
import com.bumptech.glide.Glide;
import com.example.yummyzone.fragment.itemInfoFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Menu_Adapter extends FirebaseRecyclerAdapter<Menu_tab,Menu_Adapter.myviewholder>
{
    FirebaseAuth Auth;
    FirebaseUser user;
    DatabaseReference userR, rootR;
    String username;
    public Menu_Adapter(@NonNull FirebaseRecyclerOptions<Menu_tab> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final Menu_tab model) {
        Auth = FirebaseAuth.getInstance();
        user = Auth.getCurrentUser();
        rootR = FirebaseDatabase.getInstance().getReference();
        userR = rootR.child("user");
        holder.name.setText(model.getItem_name());
        holder.pre.setText(model.getPre());
        holder.price.setText(model.getItem_price());
        // Glide.with(holder.img2.getContext()).load(model.getIcon()).into(holder.img2);
        Glide.with(holder.img1.getContext()).load(model.getItem_image()).into(holder.img1);
        Glide.with(holder.img2.getContext()).load(model.getIcon()).into(holder.img2);
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String restaurant = model.getRestaurantid();
                String name = model.getItem_name();
                String image = model.getItem_image();
                String price = model.getItem_price();
                String id = getRef(position).getKey();
                itemInfoFragment fragment = new itemInfoFragment(name, image, id, price, restaurant);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).addToBackStack(null).commit();
            }
        });
        holder.img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Foods").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot2) {
                        String s = (String) snapshot2.child(getRef(position).getKey()).getKey();
                        userR.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot keyId : snapshot.getChildren()){
                                    if (keyId.child("email").getValue().equals(user.getEmail())) {
                                        username = keyId.child("username").getValue(String.class);
                                        FirebaseDatabase.getInstance().getReference().child("fav").child(username).child(s).setValue(s);
                                    }}}

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }});}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }});}});}
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home,parent,false);
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mune_cardview,parent,false);
        return new myviewholder(view);
    }public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1,img2;
        TextView name,pre,calories,price;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.item_img);
            img2=itemView.findViewById(R.id.icon_favorite);
            name=itemView.findViewById(R.id.item_name);
            pre=itemView.findViewById(R.id.pre_time);
            price=itemView.findViewById(R.id.item_price);


        }
    }

}
