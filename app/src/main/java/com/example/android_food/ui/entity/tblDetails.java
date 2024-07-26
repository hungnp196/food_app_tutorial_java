package com.example.android_food.ui.entity;

public class tblDetails {
    int id;
    String name;
    String description;
    String calo;

    String cuisine;

    int category_id;
    int total_calo;
    String text_name;
    String image;
    String favorite;
    String url;
    int app_id;

    public tblDetails(int id, String name, String description, String calo, String cuisine, int category_id, int total_calo, String text_name, String image, String favorite, String url, int app_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.calo = calo;
        this.cuisine = cuisine;
        this.category_id = category_id;
        this.total_calo = total_calo;
        this.text_name = text_name;
        this.image = image;
        this.favorite = favorite;
        this.url = url;
        this.app_id = app_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalo() {
        return calo;
    }

    public void setCalo(String calo) {
        this.calo = calo;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getTotal_calo() {
        return total_calo;
    }

    public void setTotal_calo(int total_calo) {
        this.total_calo = total_calo;
    }

    public String getText_name() {
        return text_name;
    }

    public void setText_name(String text_name) {
        this.text_name = text_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getApp_id() {
        return app_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }
}
