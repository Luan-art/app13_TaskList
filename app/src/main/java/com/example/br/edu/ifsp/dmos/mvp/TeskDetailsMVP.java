package com.example.br.edu.ifsp.dmos.mvp;

import android.os.Bundle;

public class TeskDetailsMVP {

    interface View{
        void updateUI(String NameTesk);

        Bundle getBundle();

        void showToast(String message);

        void close();
    }

    interface Presenter{
        void deatach();

        void verifyUpdate();

        void saveArticle(String NameTesk);
    }
}
