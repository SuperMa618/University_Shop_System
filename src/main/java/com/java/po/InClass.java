package com.java.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "inclass")
public class InClass {

	@GeneratedValue(generator = "JDBC") // 自增的主键映射
	@Id
	private Integer id;
	private String className;
	private Integer collegeid;
	private Date updatetime;
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(Integer collegeid) {
		this.collegeid = collegeid;
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
		return "InClass [id=" + id + ", className=" + className + ", collegeid=" + collegeid + ", updatetime="
				+ updatetime + ", remarks=" + remarks + "]";
	}

}
