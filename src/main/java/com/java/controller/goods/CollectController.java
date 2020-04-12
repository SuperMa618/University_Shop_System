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
@RequestMapping("collect")
@Scope("prototype")
public class CollectController {

    @Autowired
    private GoodService goodService;

    /**
     * 收藏商品
     *
     * @param data
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/collect", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> goodsCollect(@RequestBody Map<String, String> data,
                                            HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        maps.put("userId", user.getId());
        maps.put("goodsId", data.get("goodsId"));
        List<Goods> goodsList = goodService.findGoods(maps);
        if (goodsList.size() < 1) {
            map.put("state", 1);
            map.put("msg", "该商品已被下单，不能收藏！");
            return map;
        }
        Integer uid = goodService.isGoodsCollect(maps);
        if (uid != null) {
            map.put("state", 1);
            map.put("msg", "不能重复收藏");
            return map;
        } else {
            goodService.goodsCollect(maps);
            map.put("state", 0);
            map.put("msg", "收藏成功！");
            return map;
        }
    }

    /**
     * 用户查看收藏的商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/selectCollect")
    public ResultMap<List<Goods>> selectCollect(Page page, @RequestParam("limit") int limit,
                                                HttpServletRequest request, ModelMap modelMap) {
        User user = (User) request.getSession().getAttribute("user");
        page.setRows(limit);
        page.setUserId(user.getId());
        List<Goods> collectList = goodService.selectPageList(page);
        int totals = goodService.selectPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Goods>>("", collectList, 0, totals);
    }


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
     * 用户删除收藏的商品
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/collectDelete")
    public Map<String, Object> collectDelete(@RequestParam String goodsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> maps = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        maps.put("userId", user.getId());
        maps.put("goodsId", goodsId);
        int result = goodService.delCollect(maps);
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
}
