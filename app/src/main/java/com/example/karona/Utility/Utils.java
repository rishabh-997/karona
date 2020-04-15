package com.example.karona.Utility;


public class Utils {
    public static String BaseUrl = "https://api.covid19india.org/";

    private Utils() {
    }

    public static ClientAPI getClientAPI() {
        return RetrofitClient.getClient(BaseUrl).create(ClientAPI.class);
    }
}
