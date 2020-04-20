package com.example.karona.Utility.covid_news;


public class Utils_news {
    public static String BaseUrl = "http://34.71.122.83:8080/";

    private Utils_news() {
    }

    public static ClientAPI_news getClientAPI() {
        return RetrofitClient_news.getClient(BaseUrl).create(ClientAPI_news.class);
    }
}
