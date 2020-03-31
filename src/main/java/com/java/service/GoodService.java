package com.java.service;

import com.java.po.Goods;

import java.util.List;
import java.util.Map;

public interface GoodService {

    void saveGoods(Goods goods);

    List<Goods> findGoods(Map map);

    void goodsCollect(Map<String, Object> maps);

    void goodsCart(Map<String, Object> maps);

    int isGoodsCollect(Map<String, Object> maps);
}
