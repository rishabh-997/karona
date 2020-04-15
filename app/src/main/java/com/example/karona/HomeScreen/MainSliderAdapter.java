package com.example.karona.HomeScreen;

import com.example.karona.R;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {


    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(R.mipmap.ic_launcher);
                break;
            case 1:
                viewHolder.bindImageSlide(R.mipmap.ic_launcher);
                break;
            case 2:
                viewHolder.bindImageSlide(R.mipmap.ic_launcher);
                break;
        }
    }
}