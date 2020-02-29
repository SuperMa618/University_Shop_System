package com.java.po;

import java.io.Serializable;

public class Admin implements Serializable {

	private Integer id;
	private String adminName;
	private String passWord;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"id=" + id +
				", adminName='" + adminName + '\'' +
				", passWord='" + passWord + '\'' +
				", email='" + email + '\'' +
				'}';
	}
}
