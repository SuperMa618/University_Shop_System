package com.java.service;

import com.java.po.Admin;
import com.java.po.Goods;
import com.java.po.Page;
import com.java.po.User;

import java.util.List;

public interface AdGoodsService {


    List<Goods> getGoodsPageList(Page page);

    int getGoodsPageCount(Page page);

    int delGoods(Integer goodsId);
}
