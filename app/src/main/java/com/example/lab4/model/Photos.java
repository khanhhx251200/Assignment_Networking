package com.example.lab4.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class Photos {
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("perpage")
    private int perpage;
    @SerializedName("total")
    private String total;
    @SerializedName("photo")
    private Photo[] photo;

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getPerpage() {
        return this.perpage;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return this.total;
    }

    public void setPhoto(Photo[] photo) {
        this.photo = photo;
    }

    public Photo[] getPhoto() {
        return this.photo;
    }


    public static Photos create(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Photos.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
