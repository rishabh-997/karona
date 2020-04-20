package com.example.karona.Utility.covid_server;

import com.example.karona.Aptos.Model.CoronaResponse;
import com.example.karona.HomeScreen.Model.NewsResponse;
import com.example.karona.Utility.Constants;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientAPI_server {

    @POST("/corona/")
    Call<CoronaResponse> sendeyeurl(
            @Body JsonObject object
    );
}
