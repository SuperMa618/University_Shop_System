package com.java.service;

import com.java.po.Goods;
import com.java.po.GoodsReview;
import com.java.po.Page;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    List<GoodsReview> getReviewPageList(Page page);

    int getReviewPageCount(Page page);

    int delReview(String id);
}
