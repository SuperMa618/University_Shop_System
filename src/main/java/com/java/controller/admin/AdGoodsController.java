package com.java.controller.admin;

import com.java.po.*;
import com.java.service.AdGoodsService;
import com.java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adGoods")
public class AdGoodsController {


    @Autowired
    private AdGoodsService adGoodsService;


    /**
     * 获取用户列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getGoods")
    public ResultMap<List<Goods>> getGoods(Page page, @RequestParam("limit") int limit,
                                          HttpServletRequest request) {
        String search = request.getParameter("keyword");
        page.setRows(limit);
        if (search != null) {
            page.setKeyWord(search);
        }
        List<Goods> collectList = adGoodsService.getGoodsPageList(page);
        int totals = adGoodsService.getGoodsPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<Goods>>("", collectList, 0, totals);
    }


    /**
     * 删除商品 会同时删除与此商品相关的其他记录
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delGoods")
    public Map<String, Object> delGoods(@RequestParam Integer goodsId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int result = adGoodsService.delGoods(goodsId);
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
     * 批量删除商品 会同时删除与此商品相关的其他记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/batchDel")
    public Map<String, Object> batchDel(@RequestBody Map<String, String> data) {
        Map<String, Object> map = new HashMap<>();
        String id = data.get("ids");
        String ids[] = id.split(",");
        for (int i = 0; i < ids.length; i++) {
            adGoodsService.delGoods(Integer.parseInt(ids[i]));
        }
        map.put("state", 1);
        map.put("msg", "删除成功");
        return map;
    }


    /**
     * 获取用户列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getGoodsReview")
    public ResultMap<List<GoodsReview>> getGoodsReview(Page page, @RequestParam("limit") int limit,
                                           HttpServletRequest request) {
        String search = request.getParameter("keyword");
        page.setRows(limit);
        if (search != null) {
            page.setKeyWord(search);
        }
        List<GoodsReview> collectList = adGoodsService.getGoodsReviewPageList(page);
        int totals = adGoodsService.getGoodsReviewPageCount(page);
        page.setTotalRecord(totals);
        return new ResultMap<List<GoodsReview>>("", collectList, 0, totals);
    }


    /**
     * 商品通过审核
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goodsYes", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Map<String, Object> goodsYes(@RequestBody Map<String, String> data, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Goods goods = new Goods(Integer.parseInt(data.get("userId")),data.get("goodsName"),data.get("price"),
                data.get("type"),data.get("describes"),data.get("picture"),data.get("time"),0);
        adGoodsService.goodsPublish(goods);
        adGoodsService.delReviewById(data.get("id"));
        map.put("state", 1);
        map.put("msg", "已通过");
        return map;
    }

    /**
     * 商品通过审核
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goodsNo")
    public Map<String, Object> goodsNo(@RequestParam Integer goodsId) {
        Map<String, Object> map = new HashMap<>();
        int a=adGoodsService.changeReviewStateById(goodsId);
        if (a > 0) {
            map.put("state", 1);
            map.put("msg", "未通过");
            return map;
        } else {
            map.put("state", 0);
            map.put("msg", "操作失败");
            return map;
        }
    }

}
