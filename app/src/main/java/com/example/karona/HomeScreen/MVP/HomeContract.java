package com.example.karona.HomeScreen.MVP;

import com.example.karona.HomeScreen.Model.Coordinates;

import java.util.List;

public class HomeContract
{
    interface view {

        void toast(String message);

        void setCordinateList(List<Coordinates> coordinates);
    }
    interface presenter {

        void getLocations();
    }
}
