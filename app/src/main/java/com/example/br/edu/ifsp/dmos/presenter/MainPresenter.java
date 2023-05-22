package com.example.br.edu.ifsp.dmos.presenter;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.br.edu.ifsp.dmos.model.dao.TeskDao;
import com.example.br.edu.ifsp.dmos.model.dao.TeskDaoSingleton;
import com.example.br.edu.ifsp.dmos.model.entities.Tesk;
import com.example.br.edu.ifsp.dmos.mvp.MainMVP;
import com.example.br.edu.ifsp.dmos.utils.Constant;
import com.example.br.edu.ifsp.dmos.view.MainActivity;
import com.example.br.edu.ifsp.dmos.view.RecyclerViewItemClickListener;
import com.example.br.edu.ifsp.dmos.view.TeskDetailsActivity;
import com.example.br.edu.ifsp.dmos.view.adapter.ItemPocketRecyclerAdapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        List<Tesk> tasks = dao.findAll();

        comparate(tasks);
        
        ItemPocketRecyclerAdapter adapter = new
                ItemPocketRecyclerAdapter(view.getContext(), dao.findAll(), this);
        adapter.setClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                tesk = dao.findAll().get(position);
                openDetails(tesk);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void comparate(List<Tesk> tasks) {
        Collections.sort(tasks, new Comparator<Tesk>() {
            @Override
            public int compare(Tesk task1, Tesk task2) {
                boolean isUrgent1 = task1.isUrgent();
                boolean isUrgent2 = task2.isUrgent();

                // Colocar as tarefas urgentes no topo
                if (isUrgent1 && !isUrgent2) {
                    return -1;
                } else if (!isUrgent1 && isUrgent2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
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

    @Override
    public void updateList() {
        List<Tesk> tasks = dao.findAll();
        boolean controladorDeLista = false;

        for (int i = 1; i < tasks.size(); i++) {
            Tesk currentTask = tasks.get(i);
            Tesk previousTask = tasks.get(i - 1);

            if (currentTask.isUrgent() && !previousTask.isUrgent()) {
                controladorDeLista = true;
                break;
            }
        }

        if (controladorDeLista) {
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            view.getContext().startActivity(intent);
        }
    }
}
