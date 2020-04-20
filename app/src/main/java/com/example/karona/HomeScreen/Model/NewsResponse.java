package com.example.karona.HomeScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("totalResults")
    @Expose
    int count;

    @SerializedName("articles")
    @Expose
    List<NewsList> newsLists;

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public List<NewsList> getNewsLists() {
        return newsLists;
    }
}
