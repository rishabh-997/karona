package com.example.karona.HomeScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelList
{
    @SerializedName("latlong")
    @Expose
    String cordinates;

    public String getCordinates() {
        return cordinates;
    }
}
