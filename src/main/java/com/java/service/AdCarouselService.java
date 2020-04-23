package com.java.service;

import com.java.po.Carousel;
import com.java.po.CommentView;
import com.java.po.Page;

import java.util.List;
import java.util.Map;

public interface AdCarouselService {


    List<Carousel> getCarouselPageList(Page page);

    int getCarouselPageCount(Page page);

    int delCarouselById(Integer commentId);

    void addCarousel(Carousel carousel);

    int changeState(Map<String, String> data);
}
