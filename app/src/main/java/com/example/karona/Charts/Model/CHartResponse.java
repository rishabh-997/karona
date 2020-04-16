package com.example.karona.Charts.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CHartResponse {
    @SerializedName("cases_time_series")
    @Expose
    List<ChartList> chartLists;

    public List<ChartList> getChartLists() {
        return chartLists;
    }
}
