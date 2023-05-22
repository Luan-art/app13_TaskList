package com.example.br.edu.ifsp.dmos.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.br.edu.ifsp.dmos.model.dao.TeskDao;
import com.example.br.edu.ifsp.dmos.model.dao.TeskDaoSingleton;
import com.example.br.edu.ifsp.dmos.model.entities.Tesk;
import com.example.br.edu.ifsp.dmos.mvp.MainMVP;
import com.example.br.edu.ifsp.dmos.utils.Constant;
import com.example.br.edu.ifsp.dmos.view.RecyclerViewItemClickListener;
import com.example.br.edu.ifsp.dmos.view.TeskDetailsActivity;
import com.example.br.edu.ifsp.dmos.view.adapter.ItemPocketRecyclerAdapter;

public class MainPresenter implements MainMVP.Presenter{

    private MainMVP.View view;
    private TeskDao dao;
    Tesk tesk;

    public MainPresenter(MainMVP.View view) {
        this.view = view;
        dao = TeskDaoSingleton.getInstance();
    }


    @Override
    public void deatach() {
        view = null;
    }

    @Override
    public void openDetails() {
        Intent intent = new Intent(view.getContext(), TeskDetailsActivity.class);
        view.getContext().startActivity(intent);
    }

    @Override
    public void openDetails(Tesk tesk) {
        Intent intent = new Intent(view.getContext(), TeskDetailsActivity.class);
        intent.putExtra(Constant.TESK_NAME, tesk.getName());
        view.getContext().startActivity(intent);
    }

    @Override
    public void populateList(RecyclerView recyclerView) {
        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);
        adapter.setClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                tesk = dao.findAll().get(position);
                openDetails(tesk);
            }
        });
        RecyclerView.LayoutManager layoutManager = new
                LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void urgenteTesk(Tesk tesk) {
        tesk.setUrgence(!tesk.isUrgent());
        dao.update(tesk.getName(), tesk);
    }

    @Override
    public void deletTesk(Tesk tesk) {
        dao.delete(tesk);
    }
}
