package com.example.bilzz.slide;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    //list of images
    public int[]  ist_image = {
            R.drawable.ahad,
            R.drawable.sahil,
            R.drawable.bilzz,
            R.drawable.ali
    };

    public String[] ist_title = {
            "Abdul Ahad Shaikh",
            "Sahil Dodai",
            "Bilal Kashani",
            "Ali Siyal"
    };
    public SlideAdapter(Context context) {
        this.context =context;
    }

    public int[] ist_backgroundcolor = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(110,49,89),
            Color.rgb(1,188,212)

    };

    public String[] ist_description = {
        "F16SW189 ","F16SW179","F16SW159 ","F16SW163"
    };



    @Override
    public int getCount() {
        return ist_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        TextView txttile = (TextView) view.findViewById(R.id.txttitle);
        TextView description  = (TextView) view.findViewById(R.id.txtdescription);
        layoutslide.setBackgroundColor((ist_backgroundcolor[position]));
        imgslide.setImageResource(ist_image[position]);
        txttile.setText(ist_title[position]);
        description.setText(ist_description[position]);
        container.addView(view);
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}