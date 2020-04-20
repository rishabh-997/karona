package com.example.karona.Utility.covid_server;


public class Utils_server {
    public static String BaseUrl = "http://34.71.122.83:8080/";

    private Utils_server() {
    }

    public static ClientAPI_server getClientAPI() {
        return RetrofitClient_server.getClient(BaseUrl).create(ClientAPI_server.class);
    }
}
