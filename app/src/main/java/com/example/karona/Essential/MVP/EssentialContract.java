package com.example.karona.Essential.MVP;

public class EssentialContract
{
    interface view {

        void showToast(String message);
    }
    interface presenter {

        void getEssentials();
    }
}
