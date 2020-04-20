package com.example.karona.Essential.MVP;

import com.example.karona.Essential.Model.EssentialList;
import com.example.karona.Essential.Model.EssentialResponse;
import com.example.karona.Utility.covid_gov.ClientAPI;
import com.example.karona.Utility.covid_gov.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EssentialPresenter implements EssentialContract.presenter {
    ClientAPI clientAPI = Utils.getClientAPI();
    EssentialContract.view mvpview;
    List<String> states = new ArrayList<>();
    List<String> categories = new ArrayList<>();

    public EssentialPresenter(EssentialContract.view mvpview) {
        this.mvpview = mvpview;
    }

    @Override
    public void getEssentials() {
        clientAPI.getResourceData().enqueue(new Callback<EssentialResponse>() {
            @Override
            public void onResponse(Call<EssentialResponse> call, Response<EssentialResponse> response) {
                if (response.isSuccessful()) {
                    List<EssentialList> essentialLists = response.body().getEssentialLists();
                    states.add(essentialLists.get(0).getState());
                    categories.add(essentialLists.get(0).getCategory());
                    boolean repeats = true;
                    for (int i = 1; i < essentialLists.size(); i++) {
                        for (int j = 0; j < states.size(); j++) {
                            if (states.get(j).equals(essentialLists.get(i).getState())) {
                                repeats = false;
                                break;
                            }
                        }
                        if (repeats)
                            states.add(essentialLists.get(i).getState());

                        repeats = true;
                        for (int j = 0; j < categories.size(); j++) {
                            if (categories.get(j).equals(essentialLists.get(i).getCategory())) {
                                repeats = false;
                                break;
                            }
                        }
                        if (repeats)
                            categories.add(essentialLists.get(i).getCategory());
                    }
                    mvpview.setUpList(essentialLists);
                    mvpview.setUpSpinner(states, categories);
                } else {
                    mvpview.showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<EssentialResponse> call, Throwable t) {
                mvpview.showToast(t.getMessage());
            }
        });
    }
}
