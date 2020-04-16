package com.example.karona.Charts.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChartList
{
    @SerializedName("totalconfirmed")
    @Expose
    String totalConfirmed;

    @SerializedName("totaldeceased")
    @Expose
    String totalDeceased;

    @SerializedName("totalrecovered")
    @Expose
    String totalRecovered;

    public String getTotalConfirmed() {
        return totalConfirmed;
    }

    public String getTotalDeceased() {
        return totalDeceased;
    }

    public String getTotalRecovered() {
        return totalRecovered;
    }
}
