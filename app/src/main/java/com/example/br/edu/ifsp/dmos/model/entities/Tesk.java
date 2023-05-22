package com.example.br.edu.ifsp.dmos.model.entities;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Tesk {

    private String name;
    private boolean favorite;

    //Relationships
    private List<Tag> tags;

    private void init(){
        tags = new ArrayList<>();
    }

    public Tesk(String name) {
        this.name = name;
        init();
    }

    public Tesk(String title, boolean favorite) {
        this.name = name;
        this.favorite = favorite;
        init();
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public void addTag(Tag tag){
        this.tags.add(tag);
    }

    public boolean removeTag(Tag tag){
        return this.tags.remove(tag);
    }

    public List<Tag> getTags(){
        return tags;
    }


    @NonNull
    @Override
    public String toString() {
        return "Title: " + name;
    }
}
