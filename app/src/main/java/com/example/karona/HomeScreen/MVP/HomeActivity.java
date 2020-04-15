package com.example.karona.HomeScreen.MVP;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.karona.Essential.MVP.EssentialActivity;
import com.example.karona.HomeScreen.MainSliderAdapter;
import com.example.karona.HomeScreen.Model.Coordinates;
import com.example.karona.HomeScreen.PicassoImageLoadingService;
import com.example.karona.databinding.ActivityHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeActivity extends AppCompatActivity implements HomeContract.view {

    Boolean check = false;
    private static final int MY_PERMISSION_REQUEST_CODE = 1000;
    MainSliderAdapter MainSliderAdapter = new MainSliderAdapter();
    PicassoImageLoadingService PicassoImageLoadingService = new PicassoImageLoadingService(HomeActivity.this);
    private ActivityHomeBinding binding;
    HomeContract.presenter presenter;
    FusedLocationProviderClient client;

    Double user_lat, user_long;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new HomePresenter(this);
        client= LocationServices.getFusedLocationProviderClient(this );

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
                checkPermissionForLocation();
                if(check) {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    presenter.getLocations();
                }
            }
        });
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCordinateList(List<Coordinates> coordinates) {
        client.getLastLocation().addOnSuccessListener(HomeActivity.this,new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null) {
                    user_lat = location.getLatitude();
                    user_long = location.getLongitude();

                    binding.progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(HomeActivity.this, EssentialActivity.class);
                    //pass user
                    //pass coordinates list
                }
            }
        });
    }

    private void checkPermissionForLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION_REQUEST_CODE);
        }
        else
            check = true;

    }
}
