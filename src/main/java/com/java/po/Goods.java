package com.java.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.io.Serializable;

public class Goods implements Serializable {

    private int id;
    private int userId;
    private String goodsName;
    private String price;
    private String type;
    private String describes;
    private String picture;
    //@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String time;

    public Goods() {
    }

    public Goods(int userId, String goodsName, String price, String type, String describes, String picture, String time) {
        this.userId = userId;
        this.goodsName = goodsName;
        this.price = price;
        this.type = type;
        this.describes = describes;
        this.picture = picture;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
