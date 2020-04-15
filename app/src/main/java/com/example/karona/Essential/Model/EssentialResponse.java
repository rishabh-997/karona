package com.example.karona.Essential.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EssentialResponse {
    @SerializedName("resources")
    @Expose
    List<EssentialList> essentialLists;

    public List<EssentialList> getEssentialLists() {
        return essentialLists;
    }
}
