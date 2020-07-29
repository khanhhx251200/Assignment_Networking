package com.example.lab4.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Photo implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("owner")
    private String owner;
    @SerializedName("secret")
    private String secret;
    @SerializedName("server")
    private String server;
    @SerializedName("farm")
    private int farm;
    @SerializedName("title")
    private String title;
    @SerializedName("ispublic")
    private int ispublic;
    @SerializedName("isfriend")
    private int isfriend;
    @SerializedName("isfamily")
    private int isfamily;
    @SerializedName("views")
    private String views;
    @SerializedName("date_faved")
    private String dateFaved;
    @SerializedName("media")
    private String media;
    @SerializedName("media_status")
    private String mediaStatus;
    @SerializedName("url_sq")
    private String urlSq;
    @SerializedName("height_sq")
    private int heightSq;
    @SerializedName("width_sq")
    private int widthSq;
    @SerializedName("url_t")
    private String urlT;
    @SerializedName("height_t")
    private int heightT;
    @SerializedName("width_t")
    private int widthT;
    @SerializedName("url_s")
    private String urlS;
    @SerializedName("height_s")
    private int heightS;
    @SerializedName("width_s")
    private int widthS;
    @SerializedName("url_q")
    private String urlQ;
    @SerializedName("height_q")
    private int heightQ;
    @SerializedName("width_q")
    private int widthQ;
    @SerializedName("url_m")
    private String urlM;
    @SerializedName("height_m")
    private int heightM;
    @SerializedName("width_m")
    private int widthM;
    @SerializedName("url_n")
    private String urlN;
    @SerializedName("height_n")
    private int heightN;
    @SerializedName("width_n")
    private int widthN;
    @SerializedName("url_z")
    private String urlZ;
    @SerializedName("height_z")
    private int heightZ;
    @SerializedName("width_z")
    private int widthZ;
    @SerializedName("url_c")
    private String urlC;
    @SerializedName("height_c")
    private int heightC;
    @SerializedName("width_c")
    private int widthC;
    @SerializedName("url_l")
    private String urlL;
    @SerializedName("height_l")
    private int heightL;
    @SerializedName("width_l")
    private int widthL;
    @SerializedName("pathalias")
    private String pathalias;

    public Photo(String url) {
        this.urlL = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getServer() {
        return this.server;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public int getFarm() {
        return this.farm;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getIspublic() {
        return this.ispublic;
    }

    public void setIsfriend(int isfriend) {
        this.isfriend = isfriend;
    }

    public int getIsfriend() {
        return this.isfriend;
    }

    public void setIsfamily(int isfamily) {
        this.isfamily = isfamily;
    }

    public int getIsfamily() {
        return this.isfamily;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getViews() {
        return this.views;
    }

    public void setDateFaved(String dateFaved) {
        this.dateFaved = dateFaved;
    }

    public String getDateFaved() {
        return this.dateFaved;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMedia() {
        return this.media;
    }

    public void setMediaStatus(String mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public String getMediaStatus() {
        return this.mediaStatus;
    }

    public void setUrlSq(String urlSq) {
        this.urlSq = urlSq;
    }

    public String getUrlSq() {
        return this.urlSq;
    }

    public void setHeightSq(int heightSq) {
        this.heightSq = heightSq;
    }

    public int getHeightSq() {
        return this.heightSq;
    }

    public void setWidthSq(int widthSq) {
        this.widthSq = widthSq;
    }

    public int getWidthSq() {
        return this.widthSq;
    }

    public void setUrlT(String urlT) {
        this.urlT = urlT;
    }

    public String getUrlT() {
        return this.urlT;
    }

    public void setHeightT(int heightT) {
        this.heightT = heightT;
    }

    public int getHeightT() {
        return this.heightT;
    }

    public void setWidthT(int widthT) {
        this.widthT = widthT;
    }

    public int getWidthT() {
        return this.widthT;
    }

    public void setUrlS(String urlS) {
        this.urlS = urlS;
    }

    public String getUrlS() {
        return this.urlS;
    }

    public void setHeightS(int heightS) {
        this.heightS = heightS;
    }

    public int getHeightS() {
        return this.heightS;
    }

    public void setWidthS(int widthS) {
        this.widthS = widthS;
    }

    public int getWidthS() {
        return this.widthS;
    }

    public void setUrlQ(String urlQ) {
        this.urlQ = urlQ;
    }

    public String getUrlQ() {
        return this.urlQ;
    }

    public void setHeightQ(int heightQ) {
        this.heightQ = heightQ;
    }

    public int getHeightQ() {
        return this.heightQ;
    }

    public void setWidthQ(int widthQ) {
        this.widthQ = widthQ;
    }

    public int getWidthQ() {
        return this.widthQ;
    }

    public void setUrlM(String urlM) {
        this.urlM = urlM;
    }

    public String getUrlM() {
        return this.urlM;
    }

    public void setHeightM(int heightM) {
        this.heightM = heightM;
    }

    public int getHeightM() {
        return this.heightM;
    }

    public void setWidthM(int widthM) {
        this.widthM = widthM;
    }

    public int getWidthM() {
        return this.widthM;
    }

    public void setUrlN(String urlN) {
        this.urlN = urlN;
    }

    public String getUrlN() {
        return this.urlN;
    }

    public void setHeightN(int heightN) {
        this.heightN = heightN;
    }

    public int getHeightN() {
        return this.heightN;
    }

    public void setWidthN(int widthN) {
        this.widthN = widthN;
    }

    public int getWidthN() {
        return this.widthN;
    }

    public void setUrlZ(String urlZ) {
        this.urlZ = urlZ;
    }

    public String getUrlZ() {
        return this.urlZ;
    }

    public void setHeightZ(int heightZ) {
        this.heightZ = heightZ;
    }

    public int getHeightZ() {
        return this.heightZ;
    }

    public void setWidthZ(int widthZ) {
        this.widthZ = widthZ;
    }

    public int getWidthZ() {
        return this.widthZ;
    }

    public void setUrlC(String urlC) {
        this.urlC = urlC;
    }

    public String getUrlC() {
        return this.urlC;
    }

    public void setHeightC(int heightC) {
        this.heightC = heightC;
    }

    public int getHeightC() {
        return this.heightC;
    }

    public void setWidthC(int widthC) {
        this.widthC = widthC;
    }

    public int getWidthC() {
        return this.widthC;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    public String getUrlL() {
        return this.urlL;
    }

    public void setHeightL(int heightL) {
        this.heightL = heightL;
    }

    public int getHeightL() {
        return this.heightL;
    }

    public void setWidthL(int widthL) {
        this.widthL = widthL;
    }

    public int getWidthL() {
        return this.widthL;
    }

    public void setPathalias(String pathalias) {
        this.pathalias = pathalias;
    }

    public String getPathalias() {
        return this.pathalias;
    }


    public static Photo create(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, Photo.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}
