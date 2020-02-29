package com.java.po;

import java.io.Serializable;

/**
 * 用户
 */
public class User implements Serializable {

    private int id;
    private String userName;
    private String passWord;
    private String tel;
    private String head;

    public User() {
    }

    public User(int id, String userName, String passWord, String tel, String head) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.tel = tel;
        this.head = head;
    }

    public User(String userName, String passWord, String tel, String head) {
        this.userName = userName;
        this.passWord = passWord;
        this.tel = tel;
        this.head = head;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
