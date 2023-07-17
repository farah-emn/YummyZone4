package com.example.yummyzone.adapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummyzone.R;
import com.example.yummyzone.classes.restaurant;
import com.example.yummyzone.fragment.MenuFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class restaurantAdapter extends FirebaseRecyclerAdapter<restaurant,restaurantAdapter.myviewholder>
{
    public  restaurantAdapter(@NonNull FirebaseRecyclerOptions<restaurant> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull final restaurant model) {
        holder.name.setText(model.getRestaurant_name());
        holder.delivery_fee.setText(model.getDelivery_fee());
        Glide.with(holder.image.getContext()).load(model.getLogo()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuFragment fragment=new MenuFragment();
                Bundle bundle=new Bundle();
                bundle.putString("restaurant_id", getRef(position).getKey());
                fragment.setArguments(bundle);
                AppCompatActivity activity=(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,fragment).commit();
            }});
        {
        if(model.getCategoryBeverages_id() !=null && model.getCategoryCoffee_id() !=null &&model.getCategoryDesserts_id() !=null &&model.getCategoryPizza_id() ==null &&model.getCategorySandwiche_id() !=null&&model.getCategoryBurgers_id()==null&&model.getCategorySeaFood_id()==null){
            holder.description.setText(model.getCategoryCoffee_id()+""+","+""+model.getCategorySandwiche_id()+""+","+""+model.getCategoryDesserts_id() +""+","+""+model.getCategoryBeverages_id());
        }
        else if (model.getCategoryCoffee_id() !=null && model.getCategoryBeverages_id() !=null&&model.getCategoryDesserts_id() ==null &&model.getCategoryPizza_id() ==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBurgers_id()==null&&model.getCategorySeaFood_id()==null ){
            holder.description.setText(model.getCategoryCoffee_id()+""+","+""+model.getCategoryBeverages_id());
        }
        else if (model.getCategoryCoffee_id() !=null && model.getCategoryBeverages_id() !=null && model.getCategoryDesserts_id() !=null&&model.getCategoryBurgers_id()==null&&model.getCategorySandwiche_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySeaFood_id()==null){
            holder.description.setText(model.getCategoryCoffee_id()+""+","+""+model.getCategoryBeverages_id()+""+","+""+model.getCategoryDesserts_id());
        }
        else if(model.getCategoryCoffee_id() ==null&&model.getCategoryBeverages_id()!=null&&model.getCategoryBurgers_id()!=null &&model.getCategorySandwiche_id()!=null &&model.getCategoryDesserts_id()==null &&model.getCategoryPizza_id()==null&&model.getCategorySeaFood_id()==null)
        {
            holder.description.setText(model.getCategoryBurgers_id()+""+","+""+model.getCategorySandwiche_id()+""+","+""+model.getCategoryBeverages_id());
        }
        else if(model.getCategoryBurgers_id() !=null && model.getCategorySandwiche_id() !=null&&model.getCategoryCoffee_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategoryDesserts_id()==null &&model.getCategoryPizza_id()==null&&model.getCategorySeaFood_id()==null){
            holder.description.setText(model.getCategoryBurgers_id()+""+","+""+model.getCategorySandwiche_id());
        }
        else if( model.getCategorySandwiche_id() !=null &&model.getCategoryBeverages_id() !=null&&model.getCategoryCoffee_id() !=null&&model.getCategoryDesserts_id()==null &&model.getCategoryPizza_id()==null&&model.getCategoryCoffee_id() ==null&&model.getCategorySeaFood_id()==null){
            holder.description.setText(model.getCategorySandwiche_id()+""+","+""+model.getCategoryBeverages_id()+""+","+""+model.getCategoryCoffee_id() );
        }
        else if (model.getCategoryPizza_id()!=null&&model.getCategorySandwiche_id() ==null &&model.getCategoryBeverages_id() ==null&&model.getCategoryCoffee_id() ==null&&model.getCategoryDesserts_id()==null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryPizza_id());
        }
        else if (model.getCategoryCoffee_id() !=null && model.getCategoryDesserts_id() !=null&&model.getCategoryBurgers_id()!=null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategorySeaFood_id()==null){
            holder.description.setText(model.getCategoryCoffee_id()+""+","+""+model.getCategoryDesserts_id()+""+","+""+model.getCategoryBurgers_id());
        }
        else if (model.getCategoryDesserts_id()!=null && model.getCategoryCoffee_id()==null&&model.getCategoryBurgers_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryDesserts_id());
        }
        else if (model.getCategoryDesserts_id()!=null && model.getCategoryCoffee_id()==null&&model.getCategoryBurgers_id()==null&&model.getCategoryPizza_id()!=null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryPizza_id()+""+","+""+model.getCategoryDesserts_id());
        }
        else if (model.getCategoryDesserts_id()!=null && model.getCategoryCoffee_id()!=null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryDesserts_id()+""+","+""+model.getCategoryCoffee_id());

        }
        else if (model.getCategoryDesserts_id()!=null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()!=null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryDesserts_id()+""+","+""+model.getCategoryCoffee_id()+""+","+""+model.getCategoryBeverages_id());

        }
        else if (model.getCategoryDesserts_id()!=null && model.getCategoryCoffee_id()!=null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() !=null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryDesserts_id()+""+","+""+model.getCategoryCoffee_id()+""+","+""+model.getCategorySandwiche_id());

        }
        else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() !=null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryBurgers_id());

        }
        else if (model.getCategoryCoffee_id() !=null && model.getCategoryDesserts_id() !=null&&model.getCategoryBurgers_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() !=null&&model.getCategoryBeverages_id()==null&&model.getCategorySeaFood_id()==null){
            holder.description.setText(model.getCategoryCoffee_id()+""+","+""+model.getCategoryDesserts_id()+""+","+""+model.getCategorySandwiche_id());
        }
        else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()!=null&&model.getCategoryBurgers_id() !=null &&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryBurgers_id()+""+","+""+model.getCategoryBeverages_id());

        }
        else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() !=null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() !=null&&model.getCategorySeaFood_id()==null) {
            holder.description.setText(model.getCategoryBurgers_id()+""+","+""+model.getCategorySandwiche_id());

        }
        else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() !=null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() !=null&&model.getCategorySeaFood_id()!=null) {
            holder.description.setText(model.getCategoryBurgers_id()+""+","+""+model.getCategorySandwiche_id()+"+"+""+model.getCategorySeaFood_id());

        }
        else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() !=null&&model.getCategorySeaFood_id()!=null) {
            holder.description.setText(model.getCategoryBurgers_id()+""+","+""+model.getCategorySeaFood_id());

        }
        else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() !=null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()!=null) {
            holder.description.setText(model.getCategorySandwiche_id()+"+"+""+model.getCategorySeaFood_id());
        }
          else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()!=null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()!=null) {
            holder.description.setText(model.getCategoryBeverages_id()+""+","+""+model.getCategorySeaFood_id());

        }   else if (model.getCategoryDesserts_id()!=null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()!=null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()!=null) {
            holder.description.setText(model.getCategoryDesserts_id()+""+","+""+model.getCategoryBeverages_id()+""+","+""+model.getCategorySeaFood_id());

        } else if (model.getCategoryDesserts_id()==null && model.getCategoryCoffee_id()==null&&model.getCategoryPizza_id()==null&&model.getCategorySandwiche_id() ==null&&model.getCategoryBeverages_id()==null&&model.getCategoryBurgers_id() ==null&&model.getCategorySeaFood_id()!=null) {
            holder.description.setText(model.getCategorySeaFood_id());

        }

        else {holder.description.setText("");}}

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_cardview,parent,false);
        return new myviewholder(view);
    }
    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView name,delivery_fee,description,description1,description2,description3;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.homeScreen_restaurant_image);
            name=itemView.findViewById(R.id.homeScreen_tv_restaurantName);
            delivery_fee=itemView.findViewById(R.id.homeScreen_tv_price);
            description=itemView.findViewById(R.id.homeScreen_tv_description);
        }
    }

}
