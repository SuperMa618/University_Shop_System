package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	/** 跳转至登陆 */
	@RequestMapping("/login")
	public String Iogin() {
		return "login";
	}

	/** 跳转至首页 */
	@RequestMapping("/index")
	public String Index() {
		return "index";
	}
	
	/** 跳转至用户首页 */
	@RequestMapping("/user/index")
	public String userIndex() {
		return "user/index";
	}
	
	/** 跳转至用户修改信息 */
	@RequestMapping("/user/update")
	public String userUpdate() {
		return "user/update";
	}
	
	/** 跳转至商品首页 */
	@RequestMapping("/goods/index")
	public String goodsIndex() {
		return "goods/index";
	}

    /** 跳转至用户提交商品 */
    @RequestMapping("/user/submitGoods")
    public String goodsSubmitGoods() {
        return "user/submitGoods";
    }

}
