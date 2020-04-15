package com.example.karona.Essential.MVP;

import com.example.karona.Essential.Model.EssentialList;

import java.util.List;

public class EssentialContract
{
    interface view {

        void showToast(String message);

        void setUpSpinner(List<String> states, List<String> categories);

        void setUpList(List<EssentialList> essentialLists);
    }
    interface presenter {

        void getEssentials();
    }
}
