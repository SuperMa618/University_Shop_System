package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	/** 跳转至登陆 */
	@RequestMapping("/login")
	public String toIogin() {
		return "login";
	}

	/** 跳转至首页 */
	@RequestMapping("/index")
	public String toStudentindex() {
		return "index";
	}
	
	/** 跳转至用户首页 */
	@RequestMapping("/user/index")
	public String toStudentSouye() {
		return "user/index";
	}
	
	/** 跳转至用户修改信息 */
	@RequestMapping("/user/update")
	public String toAdminindex() {
		return "user/update";
	}
	
	/** 跳转至查询student页 */
	@RequestMapping("/shop/index")
	public String tofindStudent() {
		return "shop/index";
	}

}
