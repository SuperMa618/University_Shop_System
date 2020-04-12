package com.java.controller.goods;

import com.java.mapper.LogMapper;
import com.java.po.*;
import com.java.service.GoodService;
import com.java.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("cart")
@Scope("prototype")
public class CartController {

    @Autowired
    private GoodService goodService;


    /**
     * 用户查看收藏或购物车的商品详情
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/collectDetail")
    public Map<String, Object> collectDetail(@RequestParam String goodsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        maps.put("goodsName", null);
        maps.put("type", null);
        maps.put("goodsId", goodsId);
        List<Goods> goodsList = goodService.findGoods(maps);
        if (request.getSession().getAttribute("goods") != null) {
            request.getSession().removeAttribute("goods");
        }
        if (goodsList.get(0) != null) {
            request.getSession().setAttribute("goods", goodsList.get(0));
            map.put("state", 1);
            return map;
        } else {
            map.put("state", 0);
            map.put("msg", "服务器走丢了");
            return map;
        }
    }

    /**
     * 加入购物车
     *
     * @param data
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cart", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> goodsCart(@RequestBody Map<String, String> data,
                                         HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        maps.put("goodsId", data.get("goodsId"));
        List<Goods> goodsList = goodService.findGoods(maps);
        if (goodsList.size() < 1) {
            map.put("state", 0);
            map.put("msg", "该商品已被下单，不能加购！");
            return map;
        }
        if (goodsList.size() > 0 && goodsList.get(0).getUserId() == user.getId()) {
            map.put("state", 0);
            map.put("msg", "这是您自己的商品哦！");
            return map;
        }
        maps.put("userId", user.getId());
        Integer uid = goodService.isGoodsCart(maps);
        if (uid != null) {
            map.put("state", 0);
            map.put("msg", "已在购物车中");
            return map;
        } else {
            goodService.goodsCart(maps);
            map.put("state", 1);
            map.put("msg", "已加入购物车！");
            return map;
        }
    }

    /**
     * 用户查看购物车的商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectCart")
    public ResultMap<List<Goods>> selectCart(Page page, @RequestParam("limit") int limit,
                                             HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<Goods> collectList = goodService.selectCartPageList(page);
        int totals = goodService.selectCartPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Goods>>("", collectList, 0, totals);
    }

    /**
     * 用户删除购物车的商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cartDelete")
    public Map<String, Object> cartDelete(@RequestParam String goodsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        maps.put("userId", user.getId());
        maps.put("goodsId", goodsId);
        int result = goodService.delCart(maps);
        if (result > 0) {
            map.put("state", 1);
            map.put("msg", "删除成功");
            return map;
        } else {
            map.put("state", 0);
            map.put("msg", "删除失败");
            return map;
        }
    }


    /**
     * 购物车批量下单
     *
     * @param data
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchBuy", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> batchBuy(@RequestBody Map<String, String> data,
                                        HttpServletRequest request) {
        //返回值
        Map<String, Object> map = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        String id = data.get("ids");
        String ids[] = id.split(",");
        for (int i = 0; i < ids.length; i++) {
            //后台传参
            Map<String, Object> maps = new HashMap<>();
            maps.put("buyerId", user.getId());
            maps.put("goodsId", ids[i]);
            maps.put("buyerTel", user.getTel());
            List<Goods> goodsList = goodService.findGoods(maps);
            maps.put("sellerId", goodsList.get(0).getUserId());
            goodService.batchBuy(maps);
        }
        map.put("state", 1);
        map.put("msg", "已下单 请在七天内完成交易！");
        return map;
    }
}
