package com.java.po;

import java.util.Date;

/**
 * 日志实体类
 * 
 * @author 云翳青
 *
 */
public class Log {

	private int id; // 主键id
	private String name;// 用户名
	private String operation;// 操作
	private String state;// 状态
	private Date uptime;

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Log(int id, String name, String operation, String state, Date uptime) {
		super();
		this.id = id;
		this.name = name;
		this.operation = operation;
		this.state = state;
		this.uptime = uptime;
	}

	public Log() {
		super();
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", name=" + name + ", operation=" + operation + ", state=" + state + ", uptime="
				+ uptime + "]";
	}

}
