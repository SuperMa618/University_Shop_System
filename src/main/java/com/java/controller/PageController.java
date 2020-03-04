package com.java.controller;

import com.java.po.Goods;
import com.java.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private GoodService goodService;
    //@ModelAttribute
    //public void findGoods() {
    //
    //}

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

    /** 跳转至用户提交商品 */
    @RequestMapping("/user/submitGoods")
    public String goodsSubmitGoods() {
        return "user/submitGoods";
    }



	/** 跳转至商品首页 */
	@RequestMapping("/goods/index")
	public String goodsIndex(ModelMap model) {
        Map<String, Object> map = new HashMap();
        map.put("goodsName", null);
        map.put("type", null);
        List<Goods> goodsList=goodService.findGoods(map);
        model.addAttribute("goodsList", goodsList);
		return "goods/index";
	}
    /** 搜索后跳转至商品首页 */
    @RequestMapping("/goods/indexSearch")
    public String goodsIndexSearch() {
        return "goods/index";
    }

    /** 搜索后跳转至商品首页 */
    @RequestMapping("/goods/detail")
    public String goodsDetail() {
        return "goods/detail";
    }

}
