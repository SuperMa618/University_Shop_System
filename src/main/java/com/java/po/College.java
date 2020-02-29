package com.java.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "College")
public class College {

	@GeneratedValue(generator = "JDBC") // 自增的主键映射
	@Id
	private Integer id;

	private String collegename;

	private Date updatetime;

	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "College [id=" + id + ", collegename=" + collegename + ", updatetime=" + updatetime + ", remarks="
				+ remarks + "]";
	}

}