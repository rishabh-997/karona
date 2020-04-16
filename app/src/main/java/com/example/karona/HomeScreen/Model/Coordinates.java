package com.example.karona.HomeScreen.Model;

import java.io.Serializable;

public class Coordinates implements Serializable
{
    Double latitude,longitude;

    public Coordinates(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
