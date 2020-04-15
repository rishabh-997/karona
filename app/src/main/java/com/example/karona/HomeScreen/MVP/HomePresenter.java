package com.example.karona.HomeScreen.MVP;

import com.example.karona.HomeScreen.Model.Coordinates;
import com.example.karona.HomeScreen.Model.TravelList;
import com.example.karona.HomeScreen.Model.TravelResponse;
import com.example.karona.Utility.ClientAPI;
import com.example.karona.Utility.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.presenter
{
    List<TravelList> travelLists = new ArrayList<>();
    HomeContract.view mvpView;
    ClientAPI clientAPI = Utils.getClientAPI();

    List<Coordinates> coordinates = new ArrayList<>();

    public HomePresenter(HomeContract.view mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void getLocations() {
        clientAPI.getTraveldata().enqueue(new Callback<TravelResponse>() {
            @Override
            public void onResponse(Call<TravelResponse> call, Response<TravelResponse> response) {
                if(response.isSuccessful()){
                    travelLists = response.body().getTravelList();
                    for(int i = 0;i<travelLists.size();i++ ){
                        String latlong = travelLists.get(i).getCordinates();
                        int comma = latlong.indexOf(",");
                        String lat = latlong.substring(0,comma);
                        String lon = latlong.substring(comma+1);
                        coordinates.add(new Coordinates(Double.valueOf(lat),Double.valueOf(lon)));
                    }
                    mvpView.setCordinateList(coordinates);
                }
                else
                    mvpView.toast(response.message());
            }

            @Override
            public void onFailure(Call<TravelResponse> call, Throwable t) {
                mvpView.toast(t.getMessage());
            }
        });
    }
}
