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
@RequestMapping("orders")
@Scope("prototype")
public class OrdersController {

    @Autowired
    private GoodService goodService;

    /**
     * 下单
     *
     * @param data
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> buy(@RequestBody Map<String, String> data,
                                   HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        maps.put("goodsId", data.get("goodsId"));
        Integer uid = goodService.isGoodsOrders(maps);
        if (uid != null) {
            map.put("state", 0);
            map.put("msg", "已下单");
            return map;
        }
        List<Goods> goodsList = goodService.findGoods(maps);
        if (goodsList.size() > 0 && goodsList.get(0).getUserId() == user.getId()) {
            map.put("state", 0);
            map.put("msg", "这是您自己的商品哦！");
            return map;
        }
        maps.put("buyerId", user.getId());
        maps.put("sellerId", goodsList.get(0).getUserId());
        maps.put("buyerTel", user.getTel());
        goodService.goodsBuy(maps);

        map.put("state", 1);
        map.put("msg", "下单成功，请在七天内完成交易！");
        return map;

    }

    /**
     * 买家删除订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buyerDelete")
    public Map<String, Object> buyerDelete(@RequestParam String goodsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            map.put("state", 0);
            map.put("msg", "未登录");
            return map;
        }
        maps.put("buyerId", user.getId());
        maps.put("goodsId", goodsId);
        int result = goodService.delOrdersByBuyer(maps);
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
     * 卖家删除订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sellerDelete")
    public Map<String, Object> sellerDelete(@RequestParam String goodsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            map.put("state", 0);
            map.put("msg", "未登录");
            return map;
        }
        maps.put("sellerId", user.getId());
        maps.put("goodsId", goodsId);
        int result = goodService.delOrdersBySeller(maps);
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
     * 用户查看自己下的订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectOrders")
    public ResultMap<List<Orders>> selectOrders(Page page, @RequestParam("limit") int limit,
                                                HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<Orders> collectList = goodService.selectOrdersPageList(page);
        int totals = goodService.selectOrdersPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Orders>>("", collectList, 0, totals);
    }

    /**
     * 用户查看自己发布的商品订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectSell")
    public ResultMap<List<Orders>> selectSell(Page page, @RequestParam("limit") int limit,
                                              HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<Orders> collectList = goodService.selectSellPageList(page);
        int totals = goodService.selectSellPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Orders>>("", collectList, 0, totals);
    }

    /**
     * 用户 完成 自己发布的商品订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/completeOrders")
    public Map<String, Object> completeOrders(@RequestParam String goodsId,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        if (request.getSession().getAttribute("user") == null) {
            map.put("state", 0);
            map.put("msg", "未登录");
            return map;
        }
        maps.put("goodsId", goodsId);
        int result = goodService.compOrders(maps);
        if (result > 0) {
            map.put("state", 1);
            map.put("msg", "交易完成");
            return map;
        } else {
            map.put("state", 0);
            map.put("msg", "删除失败");
            return map;
        }
    }


    /**
     * 用户查看历史订单
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/historyOrders")
    public ResultMap<List<Orders>> historyOrders(Page page, @RequestParam("limit") int limit,
                                                 HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<Orders> collectList = goodService.selectHistoryPageList(page);
        int totals = goodService.selectHistoryPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Orders>>("", collectList, 0, totals);
    }


}
