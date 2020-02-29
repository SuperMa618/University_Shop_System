package com.java.util;

public class LogUtil {
	private String dowhere;// 对什么进行操作
	final private String LOGIN = "进行了报到登陆";
	final private String REGISTER = "进行了信息注册";
	final private String INSERT = "进行了添加，添加数据为：";
	final private String DELETE = "进行了删除，删除数据为：";
	final private String UPDATE = "进行了修改操作，修改数据为：";
	final private String FIND = "进行了查询";
	final private String SUCCESS = "成功";
	final private String ERROR = "失败";

	public String getDowhere() {
		return dowhere;
	}

	public void setDowhere(String dowhere) {
		this.dowhere = "<" + dowhere + ">";
	}

	public String getLOGIN() {
		return LOGIN;
	}

	public String getINSERT() {
		return INSERT;
	}

	public String getDELETE() {
		return DELETE;
	}

	public String getUPDATE() {
		return UPDATE;
	}

	public String getFIND() {
		return FIND;
	}

	public String getSUCCESS() {
		return SUCCESS;
	}

	public String getERROR() {
		return ERROR;
	}

	public String getREGISTER() {
		return REGISTER;
	}

	@Override
	public String toString() {
		return "LogUtil [dowhere=" + dowhere + "]";
	}
	
}
