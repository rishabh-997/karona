package com.example.karona.Utility;

import com.example.karona.Essential.Model.EssentialResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ClientAPI {
    @GET(Constants.RESOURCE_DATA)
    Call<EssentialResponse> getResourceData();
}
