package com.java.po;

import java.io.Serializable;

public class Comment implements Serializable {

    private int id;
    private int buyerId;
    private int sellerId;
    private String sHead;
    private String content;
    //@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String date;

    public Comment() {
    }

    public Comment(int id, int buyerId, int sellerId, String sHead, String content, String date) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.sHead = sHead;
        this.content = content;
        this.date = date;
    }

    public String getsHead() {
        return sHead;
    }

    public void setsHead(String sHead) {
        this.sHead = sHead;
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
