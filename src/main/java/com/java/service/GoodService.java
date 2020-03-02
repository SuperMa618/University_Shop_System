package com.java.service;

import com.java.po.Goods;

import java.util.List;

public interface GoodService {

    void saveGoods(Goods goods);

    List<Goods> findAllGoods();
}
