package com.java.mapper;

import com.java.po.Goods;
import com.java.po.Orders;
import com.java.po.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    //保存提交商品信息
    void saveGoods(@Param("goods") Goods goods);

    List<Goods> findGoods(Map map);

    void goodsCollect(Map<String, Object> maps);

    void goodsCart(Map<String, Object> maps);

    Integer isGoodsCollect(Map<String, Object> maps);

    Integer isGoodsCart(Map<String, Object> maps);

    List<Goods> selectPageList(Page page);

    int selectPageCount(Page page);

    int delCollect(Map<String, Object> maps);

    int delCart(Map<String, Object> maps);

    List<Goods> selectCartPageList(Page page);

    int selectCartPageCount(Page page);

    int goodsBuy(Map<String, Object> maps);

    List<Orders> selectOrdersPageList(Page page);

    int selectOrdersPageCount(Page page);

    Integer isGoodsOrders(Map<String, Object> maps);

    List<Orders> selectSellPageList(Page page);

    int selectSellPageCount(Page page);

    int compOrders(Map<String, Object> maps);

    int delOrdersByBuyer(Map<String, Object> maps);

    int delOrdersBySeller(Map<String, Object> maps);

    void changeGoodsStateZero(String goodsId);

    void changeGoodsStateOne(String goodsId);

    void changeGoodsStateTwo(String goodsId);

    List<Orders> selectHistoryPageList(Page page);

    int selectHistoryPageCount(Page page);
}
