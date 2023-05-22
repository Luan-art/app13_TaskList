package com.example.br.edu.ifsp.dmos.mvp;

import android.os.Bundle;

public interface TeskDetailsMVP {

    interface View{
        void updateUI(String NameTesk);

        Bundle getBundle();

        void showToast(String message);

        void close();
    }

    interface Presenter{

        void deatach();

        void verifyUpdate();

        void saveTesk (String NameTesk);
    }
}
