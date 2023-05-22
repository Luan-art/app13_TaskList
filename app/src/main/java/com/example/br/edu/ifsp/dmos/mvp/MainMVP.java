package com.example.br.edu.ifsp.dmos.mvp;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.br.edu.ifsp.dmos.model.entities.Tesk;

public interface MainMVP {

    interface View{
        Context getContext();
    }

    interface Presenter{
        void deatach();

        void openDetails();

        void openDetails(Tesk tesk);

        void populateList(RecyclerView recyclerView);

        void urgenteTesk(Tesk tesk);

        void deletTesk(Tesk tesk);
    }
}
