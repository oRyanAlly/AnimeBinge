package com.animebinge.rally0565.animebinge;

/**
 * Created by Ryan on 2017-10-18.
 */

public class AnimeShow {
    private int id;
    private byte[] image;
    private String name;

    public AnimeShow(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public AnimeShow() {

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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
