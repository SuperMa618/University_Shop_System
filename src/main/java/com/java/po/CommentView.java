package com.java.po;

import java.io.Serializable;

public class CommentView implements Serializable {

    private int id;
    private String buyer;
    private String seller;
    private String tel;
    private String content;
    //@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String date;

    public CommentView() {
    }

    public CommentView(int id, String buyer, String seller, String tel, String content, String date) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.tel = tel;
        this.content = content;
        this.date = date;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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
