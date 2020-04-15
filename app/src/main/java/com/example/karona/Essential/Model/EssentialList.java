package com.example.karona.Essential.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EssentialList {
    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("descriptionandorserviceprovided")
    @Expose
    private String description;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("nameoftheorganisation")
    @Expose
    private String name;

    public String getCategory() {
        return category;
    }

    public String getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public String getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }
}
