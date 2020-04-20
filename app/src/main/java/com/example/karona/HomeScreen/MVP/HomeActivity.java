package com.example.karona.HomeScreen.MVP;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.karona.Aptos.MVP.CoronaActivity;
import com.example.karona.Charts.MVP.ChartActivity;
import com.example.karona.Essential.MVP.EssentialActivity;
import com.example.karona.HomeScreen.Model.Coordinates;
import com.example.karona.HomeScreen.Model.NewsList;
import com.example.karona.TravelHistory.MapsActivity;
import com.example.karona.databinding.ActivityHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeContract.view, NewsAdapter.ClickListener {

    private static final int MY_PERMISSION_REQUEST_CODE = 1000;
    Boolean check = false;
    HomeContract.presenter presenter;
    FusedLocationProviderClient client;
    Double user_lat, user_long;
    private ActivityHomeBinding binding;
    List<NewsList> newsLists = new ArrayList<>();
    NewsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new HomePresenter(this);
        client = LocationServices.getFusedLocationProviderClient(this);

        binding.newsView.setHasFixedSize(true);
        binding.newsView.setLayoutManager(new LinearLayoutManager(this));

        presenter.getNews();

        binding.essential.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, EssentialActivity.class));
            }
        });

        binding.travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CoronaActivity.class));
            }
        });

        binding.chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ChartActivity.class));
            }
        });

        binding.infection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, WebFragment.class));

            }
        });
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setCordinateList(final List<Coordinates> coordinates) {
        client.getLastLocation().addOnSuccessListener(HomeActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    user_lat = location.getLatitude();
                    user_long = location.getLongitude();

                    binding.progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(HomeActivity.this, MapsActivity.class);
                    intent.putExtra("user_lat", user_lat);
                    intent.putExtra("user_long", user_long);
                    intent.putExtra("all_cordiantes", (Serializable) coordinates);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void showNews(List<NewsList> news) {
        newsLists.clear();
        newsLists = news;

        adapter = new NewsAdapter(newsLists,this, this);
        binding.newsView.setAdapter(adapter);

    }

    private void checkPermissionForLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_CODE);
        } else
            check = true;

    }

    @Override
    public void newsClicked(int position) {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(newsLists.get(position).getUrl()));
        startActivity(intent);
    }
}
