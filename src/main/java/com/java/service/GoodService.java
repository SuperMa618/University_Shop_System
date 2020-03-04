package com.java.service;

import com.java.po.Goods;

import java.util.List;
import java.util.Map;

public interface GoodService {

    void saveGoods(Goods goods);

    List<Goods> findGoods(Map map);
}
