package com.example.android_food.ui.entity;

public class tblCategoryEntity {

    int id;
    String name;

    String url;

    public tblCategoryEntity(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
