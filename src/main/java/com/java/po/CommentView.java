package com.java.po;

import java.io.Serializable;

public class CommentView implements Serializable {

    private int id;
    private int buyerId;
    private int sellerId;
    private String tel;
    private String content;
    //@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String date;

    public CommentView() {
    }

    public CommentView(int id, int buyerId, int sellerId, String tel, String content, String date) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.tel = tel;
        this.content = content;
        this.date = date;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
