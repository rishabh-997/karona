package com.example.karona.Aptos.MVP;

import com.example.karona.Aptos.Model.CoronaResponse;
import com.example.karona.Utility.covid_gov.ClientAPI;
import com.example.karona.Utility.covid_gov.Utils;
import com.example.karona.Utility.covid_server.ClientAPI_server;
import com.example.karona.Utility.covid_server.Utils_server;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoronaPresenter implements CoronaContract.presenter {
    private CoronaContract.view mvpview;
    private ClientAPI_server clientAPI = Utils_server.getClientAPI();

    CoronaPresenter(CoronaContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getResponse(JsonObject jsonObject) {
        clientAPI.sendeyeurl(jsonObject).enqueue(new Callback<CoronaResponse>() {
            @Override
            public void onResponse(Call<CoronaResponse> call, Response<CoronaResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getNum() != 0) {
                        mvpview.showResult(response.body());
                    } else
                        mvpview.toast("Error Aa Gaya");
                } else {
                    mvpview.toast(response.message());
                }
            }

            @Override
            public void onFailure(Call<CoronaResponse> call, Throwable t) {
                mvpview.toast(t.getMessage());
            }
        });
    }
}
