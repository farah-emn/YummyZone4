package com.example.yummyzone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummyzone.R;
import com.example.yummyzone.classes.newOrder;
import com.example.yummyzone.classes.orderItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class orderDetailsAdapter extends RecyclerView.Adapter<orderDetailsAdapter.myviewholder>{
    ArrayList<orderItem> orderItemList;
    Context context;
    String resName;
    DatabaseReference orderR, rootR;

    public orderDetailsAdapter(Context context, ArrayList<orderItem> orderItemList, String resName){
        this.context = context;
        this.orderItemList = orderItemList;
        this.resName = resName;
    }
    @NonNull
    @Override
    public orderDetailsAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_details_item, parent, false);
        rootR = FirebaseDatabase.getInstance().getReference();
        orderR = rootR.child("order");
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderDetailsAdapter.myviewholder holder, int position) {
        orderItem o = orderItemList.get(position);
        holder.tv_ItemName.setText(o.getName());
        holder.tv_num.setText(o.getNum());
    }

    @Override
    public int getItemCount() {
        return orderItemList.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        TextView tv_ItemName, tv_num;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tv_ItemName = itemView.findViewById(R.id.restaurant_orderDetailsItem_tv_itemName);
            tv_num = itemView.findViewById(R.id.restaurant_orderDetailsItem_tv_NumOfItem);

        }
    }
}
