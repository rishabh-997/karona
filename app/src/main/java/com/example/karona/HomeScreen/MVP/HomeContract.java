package com.example.karona.HomeScreen.MVP;

import com.example.karona.HomeScreen.Model.Coordinates;
import com.example.karona.HomeScreen.Model.NewsList;

import java.util.List;

public class HomeContract
{
    interface view {

        void toast(String message);

        void setCordinateList(List<Coordinates> coordinates);

        void showNews(List<NewsList> newsLists);
    }
    interface presenter {

        void getLocations();

        void getNews();
    }
}
