package com.animebinge.rally0565.animebinge;

/**
 * Created by Ryan on 2017-10-18.
 */

public class AnimeShow {
    private int id;
    private byte[] image;
    private String sName;
    private double avgScore;
    private String type;
    private String status;
    private String eps;
    private String aired;
    private String age;
    private String desc;

    public AnimeShow() {

    }

    public AnimeShow(int id, String name, byte[] image, double avgScore, String type, String status,
                     String eps, String aired, String age, String desc) {
        this.id = id;
        this.sName = name;
        this.image = image;
        this.avgScore = avgScore;
        this.type = type;
        this.status = status;
        this.eps = eps;
        this.aired = aired;
        this.age = age;
        this.desc = desc;
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

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
