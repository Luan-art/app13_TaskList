package com.example.br.edu.ifsp.dmos.presenter;

import android.os.Bundle;

import com.example.br.edu.ifsp.dmos.model.dao.TeskDao;
import com.example.br.edu.ifsp.dmos.model.dao.TeskDaoSingleton;
import com.example.br.edu.ifsp.dmos.model.entities.Tesk;
import com.example.br.edu.ifsp.dmos.mvp.TeskDetailsMVP;
import com.example.br.edu.ifsp.dmos.utils.Constant;

public class TeskDetailsPresenter implements TeskDetailsMVP.Presenter {

    private TeskDetailsMVP.View view;
    private Tesk tesk;
    private TeskDao dao;

    public TeskDetailsPresenter(TeskDetailsMVP.View view) {
        this.view = view;
        tesk = null;
        dao = TeskDaoSingleton.getInstance();
    }

    @Override
    public void deatach() {
        this.view = null;
    }

    @Override
    public void verifyUpdate() {
        String name;
        Bundle bundle = view.getBundle();
        if(bundle != null){
            name = bundle.getString(Constant.TESK_NAME);
            tesk = dao.findByName(name);
            view.updateUI(tesk.getName());
        }
    }

    @Override
    public void saveTesk(String name) {

        if(tesk == null){
            //New tesk
            tesk = new Tesk(name);
            dao.create(tesk);
            view.showToast("Nova tesk adicionada com sucesso.");
            view.close();
        }else{
            //Update a existent tesk
            String oldName = tesk.getName();
            Tesk newTesk = new Tesk(name, tesk.isUrgent());
            if(dao.update(oldName, newTesk)){
                view.showToast("Tarefa atualizado com sucesso.");
                view.close();
            }else{
                view.showToast("Erro ao atualizar a tarefas.");
            }
        }
    }
}
