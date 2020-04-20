package com.example.karona.HomeScreen.MVP;

import com.example.karona.HomeScreen.Model.Coordinates;
import com.example.karona.HomeScreen.Model.NewsList;
import com.example.karona.HomeScreen.Model.NewsResponse;
import com.example.karona.HomeScreen.Model.TravelList;
import com.example.karona.HomeScreen.Model.TravelResponse;
import com.example.karona.Utility.covid_gov.ClientAPI;
import com.example.karona.Utility.covid_gov.Utils;
import com.example.karona.Utility.covid_news.ClientAPI_news;
import com.example.karona.Utility.covid_news.Utils_news;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements HomeContract.presenter
{
    private List<TravelList> travelLists = new ArrayList<>();
    private HomeContract.view mvpView;
    private ClientAPI clientAPI = Utils.getClientAPI();
    private ClientAPI_news newsAPI = Utils_news.getClientAPI();

    private List<Coordinates> coordinates = new ArrayList<>();

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
                        String latlong = travelLists.get(i).getCordinates().trim();
                        if(!latlong.trim().isEmpty()) {
                            int comma = latlong.indexOf(",");
                            String lat = latlong.substring(0,comma);
                            String lon = latlong.substring(comma+1);
                            if(!lat.contains(",") && !lon.contains(","))
                                coordinates.add(new Coordinates(Double.valueOf(lat), Double.valueOf(lon)));
                        }
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

    @Override
    public void getNews() {
        newsAPI.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(response.isSuccessful()) {
                    if(response.body().getStatus().equals("ok")) {
                        mvpView.showNews(response.body().getNewsLists());
                    }
                    else
                        mvpView.toast("Error fetching news");
                } else {
                    mvpView.toast(response.message());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                mvpView.toast(t.getMessage());
            }
        });
    }
}
