package com.example.karona.Charts.MVP;

import com.example.karona.Charts.Model.CHartResponse;
import com.example.karona.Utility.covid_gov.ClientAPI;
import com.example.karona.Utility.covid_gov.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChartPresenter implements ChartContract.presenter {
    ChartContract.view mvpView;
    ClientAPI clientAPI = Utils.getClientAPI();

    public ChartPresenter(ChartContract.view mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void getChartData() {
        clientAPI.getChartData().enqueue(new Callback<CHartResponse>() {
            @Override
            public void onResponse(Call<CHartResponse> call, Response<CHartResponse> response) {
                if(response.isSuccessful()) {
                    mvpView.showChart(response.body().getChartLists());
                } else {
                    mvpView.toast(response.message());
                }
            }

            @Override
            public void onFailure(Call<CHartResponse> call, Throwable t) {
                mvpView.toast(t.getMessage());
            }
        });
    }
}
