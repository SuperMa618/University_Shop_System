package com.java.service;

import com.java.po.*;

import java.util.List;

public interface AdGoodsService {


    List<Goods> getGoodsPageList(Page page);

    int getGoodsPageCount(Page page);

    int delGoods(Integer goodsId);

    List<GoodsReview> getGoodsReviewPageList(Page page);

    int getGoodsReviewPageCount(Page page);

    int changeReviewStateById(int goodsId);

    void goodsPublish(Goods goods);

    void delReviewById(String id);
}
