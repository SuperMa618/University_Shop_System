package com.java.service;

import com.java.po.Goods;
import com.java.po.Orders;
import com.java.po.Page;

import java.util.List;
import java.util.Map;

public interface GoodService {

    void saveGoods(Goods goods);

    List<Goods> findGoods(Map map);

    void goodsCollect(Map<String, Object> maps);

    void goodsCart(Map<String, Object> maps);

    Integer isGoodsCollect(Map<String, Object> maps);

    Integer isGoodsCart(Map<String, Object> maps);

    void goodsBuy(Map<String, Object> maps);

    List<Goods> selectPageList(Page page);

    int selectPageCount(Page page);

    int delCollect(Map<String, Object> maps);

    int delCart(Map<String, Object> maps);

    List<Goods> selectCartPageList(Page page);

    int selectCartPageCount(Page page);

    List<Orders> selectOrdersPageList(Page page);

    int selectOrdersPageCount(Page page);

    Integer isGoodsOrders(Map<String, Object> maps);

    List<Orders> selectSellPageList(Page page);

    int selectSellPageCount(Page page);

    int delOrders(Map<String, Object> maps);

    void batchBuy(Map<String, Object> maps);

    int compOrders(Map<String, Object> maps);
}
