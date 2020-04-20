package com.example.karona.Aptos.MVP;

import com.example.karona.Aptos.Model.CoronaResponse;
import com.google.gson.JsonObject;

public class CoronaContract
{
    interface view{

        void toast(String error_aa_gaya);

        void showResult(CoronaResponse body);
    }
    interface presenter{
        void getResponse(JsonObject jsonObject);
    }
}
