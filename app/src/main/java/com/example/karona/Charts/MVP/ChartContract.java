package com.example.karona.Charts.MVP;

import com.example.karona.Charts.Model.ChartList;

import java.util.List;

public class ChartContract
{
    interface view {

        void toast(String message);

        void showChart(List<ChartList> chartLists);
    }
    interface presenter {
        void getChartData();
    }
}
