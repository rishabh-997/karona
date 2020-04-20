package com.example.karona.Aptos.Model;

import com.google.gson.annotations.SerializedName;

public class CoronaResponse {
    
    @SerializedName("result")
    int num;

    public CoronaResponse(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
