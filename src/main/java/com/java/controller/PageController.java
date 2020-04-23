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


	/** 后台跳转至用户管理 */
	@RequestMapping("/admin/user")
	public String adminUser() {
		return "/admin/user";
	}

	/** 后台跳转至商品管理*/
	@RequestMapping("/admin/goods")
	public String adminGoods() {
		return "/admin/goods";
	}

	/** 后台跳转至留言管理*/
	@RequestMapping("/admin/comment")
	public String adminComment() {
		return "/admin/comment";
	}

	/** 后台跳转至留言管理*/
	@RequestMapping("/admin/review")
	public String adminReview() {
		return "/admin/review";
	}

	/** 后台跳转至留言管理*/
	@RequestMapping("/admin/carousel")
	public String adminCarousel() {
		return "/admin/carousel";
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

	/** 跳转至用户收藏夹 */
	@RequestMapping("/user/collect")
	public String userCollect() {
		return "/user/collect";
	}

	/** 跳转至用户购物车 */
	@RequestMapping("/user/cart")
	public String userCart() {
		return "/user/cart";
	}

	/** 跳转至用户卖方订单 */
	@RequestMapping("user/ordersell")
	public String userSell() {
		return "user/ordersell";
	}

	/** 跳转至用户买方订单 */
	@RequestMapping("/user/orders")
	public String userOrders() {
		return "/user/orders";
	}

	/** 跳转至用户历史订单 */
	@RequestMapping("/user/orderHistory")
	public String userHistory() {
		return "/user/orderHistory";
	}

	/** 跳转至用户发布的商品 */
	@RequestMapping("/user/orderPublish")
	public String userPublish() {
		return "/user/orderPublish";
	}

	/** 跳转至用户发布的商品 */
	@RequestMapping("/user/review")
	public String userReview() {
		return "/user/review";
	}

    /** 跳转至用户提交商品 */
    @RequestMapping("/user/submitGoods")
    public String goodsSubmitGoods() {
        return "user/submitGoods";
    }

	/** 跳转至我的留言 */
	@RequestMapping("/user/comment")
	public String myComment() {
		return "user/comment";
	}

	/** 跳转至买家的留言 */
	@RequestMapping("/user/commentForS")
	public String commentForS() {
		return "user/commentForS";
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

    /** 跳转至商品详情 */
    @RequestMapping("/goods/detail")
    public String goodsDetail() {
        return "goods/detail";
    }

}
