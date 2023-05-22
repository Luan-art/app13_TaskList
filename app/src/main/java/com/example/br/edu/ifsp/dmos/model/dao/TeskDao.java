package com.example.br.edu.ifsp.dmos.model.dao;

import com.example.br.edu.ifsp.dmos.model.entities.Tag;
import com.example.br.edu.ifsp.dmos.model.entities.Tesk;

import java.util.List;

public interface TeskDao {

    void create(Tesk tesk);

    boolean update(String oldName, Tesk tesk);

    boolean delete(Tesk tesk);

    Tesk findByName(String name);

    List<Tesk> findByTag(Tag tag);

    List<Tesk> findAll();
}
