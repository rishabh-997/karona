package com.example.karona.Utility.covid_news;

import com.example.karona.HomeScreen.Model.NewsResponse;
import com.example.karona.Utility.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientAPI_news {

    @GET(Constants.NEWS_DATA)
    Call<NewsResponse> getNews();
}
