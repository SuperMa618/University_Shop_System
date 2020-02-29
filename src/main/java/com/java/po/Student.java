package com.java.po;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 学生实体类
 * 
 * @author 云翳青
 *
 */
//@Table：声明此对象映射到数据库的数据表，通过它可以为实体指定表
@Table(name = "student")
public class Student {

	@GeneratedValue(strategy=GenerationType.IDENTITY) // 自增的主键映射
	@Id
	private Integer id; // 主键
	private String username;// 用户名
	private Integer studentid;// 学号
	private String password;// 密码
	private Integer sex;// 性别
	private String images;// 图片
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date birthdate;// 生日
	private String phone;// 电话
	private String address;// 地址
	private String college;// 所在学院
	private String inclass;// 班级
	private String qq;// qq
	private Integer state;// 状态
	private Date updatetime;// 更新时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date reporttotime;// 报到时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getInclass() {
		return inclass;
	}

	public void setInclass(String inclass) {
		this.inclass = inclass;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public Date getReporttotime() {
		return reporttotime;
	}

	public void setReporttotime(Date reporttotime) {
		this.reporttotime = reporttotime;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", studentid=" + studentid + ", password=" + password
				+ ", sex=" + sex + ", images=" + images + ", birthdate=" + birthdate + ", phone=" + phone + ", address="
				+ address + ", college=" + college + ", inclass=" + inclass + ", qq=" + qq + ", state=" + state
				+ ", updatetime=" + updatetime + ", reporttotime=" + reporttotime + "]";
	}

}
