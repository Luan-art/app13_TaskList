package com.example.br.edu.ifsp.dmos.model.dao;

import com.example.br.edu.ifsp.dmos.model.entities.Tag;
import com.example.br.edu.ifsp.dmos.model.entities.Tesk;

import java.util.ArrayList;
import java.util.List;

public class TeskDaoSingleton implements TeskDao{
    private static TeskDaoSingleton instance = null;
    private List<Tesk> dataset;

    private TeskDaoSingleton() {
        dataset = new ArrayList<>();
    }

    public static TeskDaoSingleton getInstance(){
        if(instance == null)
            instance = new TeskDaoSingleton();
        return instance;
    }

    @Override
    public void create(Tesk tesk) {
        if(tesk != null){
            dataset.add(tesk);
        }
    }

    @Override
    public boolean update(String oldName, Tesk tesk) {
        Tesk inDataset;
        inDataset = dataset.stream()
                .filter(tesk1 -> tesk1.getName().equals(oldName))
                .findAny()
                .orElse(null);
        if(inDataset != null){
            inDataset.setName(tesk.getName());
            inDataset.setUrgence(tesk.isUrgent());
            inDataset.getTags().clear();
            inDataset.getTags().addAll(tesk.getTags());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Tesk tesk) {
        return dataset.remove(tesk);
    }

    @Override
    public Tesk findByName(String name) {
        return dataset.stream()
                .filter(tesk -> tesk.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Tesk> findByTag(Tag tag) {
        List<Tesk> selection = new ArrayList<>();
        for(Tesk a : dataset){
            for(Tag t : a.getTags()){
                if(t.getTagName().equals(tag.getTagName())){
                    selection.add(a);
                }
            }
        }
        return selection;    }

    @Override
    public List<Tesk> findAll() {
        return dataset;
    }
}
