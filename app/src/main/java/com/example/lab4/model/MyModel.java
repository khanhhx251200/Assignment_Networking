package com.example.lab4.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class MyModel {
    @SerializedName("photos")
    private Photos photos;
    @SerializedName("stat")
    private String stat;

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public Photos getPhotos() {
        return this.photos;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getStat() {
        return this.stat;
    }


    public static MyModel create(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, MyModel.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
