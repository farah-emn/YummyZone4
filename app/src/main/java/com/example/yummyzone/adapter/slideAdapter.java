package com.example.yummyzone.adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.yummyzone.R;

public class slideAdapter extends PagerAdapter {

    Context context;

    public slideAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_screen, container, false);
        ImageView image = view.findViewById(R.id.start_image);
        ImageView c1 = view.findViewById(R.id.start_img_c1);
        ImageView c2 = view.findViewById(R.id.start_img_c2);
        ImageView c3 = view.findViewById(R.id.start_img_c3);
        TextView tv_title = view.findViewById(R.id.start_tv_title);
        TextView tv_desc = view.findViewById(R.id.start_tv_description);
        switch (position){
            case 0:
                image.setImageResource(R.drawable.image1);
                c1.setImageResource(R.drawable.orange_c);
                c2.setImageResource(R.drawable.gray_c);
                c3.setImageResource(R.drawable.gray_c);
                tv_title.setText("Search for your favorite food");
                tv_desc.setText("search for your favorite restaurant and food in your country.");
                break;

            case 1:
                image.setImageResource(R.drawable.image2);
                c1.setImageResource(R.drawable.gray_c);
                c2.setImageResource(R.drawable.orange_c);
                c3.setImageResource(R.drawable.gray_c);
                tv_title.setText("Order any day, any time ");
                tv_desc.setText("you can order any time and we will deliver your order.");
                break;

            case 2:
                image.setImageResource(R.drawable.image3);
                c1.setImageResource(R.drawable.gray_c);
                c2.setImageResource(R.drawable.gray_c);
                c3.setImageResource(R.drawable.orange_c);
                tv_title.setText("Fast delivery to your place");
                tv_desc.setText("fast delivery to your home, office and where you are.");
                break;
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


    }
