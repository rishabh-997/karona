package com.example.karona.HomeScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TravelResponse
{
    @SerializedName("travel_history")
    @Expose
    private List<TravelList> travelList;

    public List<TravelList> getTravelList() {
        return travelList;
    }
}
