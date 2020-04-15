package com.example.karona.HomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.karona.Essential.MVP.EssentialActivity;
import com.example.karona.databinding.ActivityHomeBinding;

import ss.com.bannerslider.Slider;

public class HomeActivity extends AppCompatActivity {

    MainSliderAdapter MainSliderAdapter = new MainSliderAdapter();
    PicassoImageLoadingService PicassoImageLoadingService = new PicassoImageLoadingService(HomeActivity.this);
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Slider.init(PicassoImageLoadingService);
        binding.bannerSlider.setAdapter(MainSliderAdapter);

        binding.essential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, EssentialActivity.class));
            }
        });

        binding.travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, EssentialActivity.class));
            }
        });
    }
}
