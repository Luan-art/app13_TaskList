package com.example.br.edu.ifsp.dmos.model.dao;

import com.example.br.edu.ifsp.dmos.model.entities.Tag;

import java.util.List;

public interface TagDao {

    void create(Tag tag);

    boolean delete(Tag tag);

    Tag find(String tagName);

    List<Tag> findAll();

}
