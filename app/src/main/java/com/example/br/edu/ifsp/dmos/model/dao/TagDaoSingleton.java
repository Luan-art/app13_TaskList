package com.example.br.edu.ifsp.dmos.model.dao;

import com.example.br.edu.ifsp.dmos.model.entities.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagDaoSingleton implements TagDao{

    private static TagDaoSingleton instance;
    private List<Tag> dataset;

    private TagDaoSingleton(){
        dataset = new ArrayList<>();
    }

    public static TagDaoSingleton getInstance(){
        if(instance == null)
            instance = new TagDaoSingleton();
        return instance;
    }

    @Override
    public boolean delete(Tag tag) {
        return dataset.remove(tag);
    }

    @Override
    public void create(Tag tag) {
        if(tag != null){
            if(find(tag.getTagName()) == null){
                dataset.add(tag);
            }
        }
    }

    @Override
    public Tag find(String tagName) {
        return dataset.stream()
                .filter(tag -> tag.getTagName().equals(tagName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Tag> findAll() {
        return dataset;
    }
}
