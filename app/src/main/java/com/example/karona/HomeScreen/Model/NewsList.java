package com.example.karona.HomeScreen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsList {
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    @Expose
    String desc;
    @SerializedName("url")
    @Expose
    String url;
    @SerializedName("urlToImage")
    @Expose
    String urlToImage;
    @SerializedName("author")
    @Expose
    String author;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getAuthor() {
        return author;
    }
}
