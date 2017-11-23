package com.animebinge.rally0565.animebinge;

/**
 * Created by Ryan on 2017-10-18.
 */

public class AnimeShow {
    private int id;
    private byte[] image;
    private String sName;

    public AnimeShow() {

    }
    public AnimeShow(int id, String name, byte[] image) {
        this.id = id;
        this.sName = name;
        this.image = image;
    }
    public AnimeShow(String name, byte[] image) {
        this.sName = name;
        this.image = image;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return sName;
    }

    public void setName(String name) {
        this.sName = name;
    }
}
