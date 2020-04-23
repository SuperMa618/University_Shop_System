package com.java.po;

import java.io.Serializable;

public class Carousel implements Serializable {

    private int id;
    private String picture;
    private String date;
    private String state;

    public Carousel() {
    }

    public Carousel(int id, String picture, String date, String state) {
        this.id = id;
        this.picture = picture;
        this.date = date;
        this.state = state;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
