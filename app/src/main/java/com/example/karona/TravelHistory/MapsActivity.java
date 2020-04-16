package com.example.karona.TravelHistory;

import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.karona.HomeScreen.Model.Coordinates;
import com.example.karona.R;
import com.example.karona.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    ActivityMapsBinding binding;
    private GoogleMap mMap;
    Double user_lat, user_long;
    List<Coordinates> coordinatesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user_lat = getIntent().getDoubleExtra("user_lat",0.00000);
        user_long = getIntent().getDoubleExtra("user_long",0.0000);
        coordinatesList = (List<Coordinates>) getIntent().getExtras().getSerializable("all_cordiantes");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        float distance = 100000.0f;

        LatLng mine = new LatLng(user_lat, user_long);
        Location myLocation = new Location("");
        myLocation.setLatitude(user_lat);
        myLocation.setLongitude(user_long);

        LatLng myMarker = new LatLng(user_lat,user_long);
        mMap.addMarker(new MarkerOptions()
                .position(myMarker)
                .title("YOur Position")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myMarker));

        for(int i = 0;i<coordinatesList.size();i++) {
            LatLng marker = new LatLng(coordinatesList.get(i).getLatitude(), coordinatesList.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(marker));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));

            LatLng infection = new LatLng(coordinatesList.get(i).getLatitude(), coordinatesList.get(i).getLongitude());
            Location infectedLocation = new Location("");
            infectedLocation.setLatitude(coordinatesList.get(i).getLatitude());
            infectedLocation.setLongitude(coordinatesList.get(i).getLongitude());

            float sample_distance=(myLocation.distanceTo(infectedLocation))/1000;
            if(sample_distance<distance)
                distance = sample_distance;
        }

        binding.distance.setText("Nearest Infection is "+distance+" kms away from you.");
    }
}
