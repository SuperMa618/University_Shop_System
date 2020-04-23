package com.java.service.Impl;

import com.java.mapper.AdCarouselMapper;
import com.java.mapper.AdCommentMapper;
import com.java.po.Carousel;
import com.java.po.CommentView;
import com.java.po.Page;
import com.java.service.AdCarouselService;
import com.java.service.AdCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adCarouselService")
public class AdCarouselServiceImpl implements AdCarouselService {

    @Autowired
    private AdCarouselMapper adCarouselMapper;

    @Override
    public List<Carousel> getCarouselPageList(Page page) {
        return adCarouselMapper.getCarouselPageList(page);
    }

    @Override
    public int delCarouselById(Integer id) {
        return adCarouselMapper.delCarouselById(id);
    }

    @Override
    public int changeState(Map<String, String> data) {
        return adCarouselMapper.changeState(data);
    }

    @Override
    public void addCarousel(Carousel carousel) {
        adCarouselMapper.insertCarousel(carousel);
    }

    @Override
    public int getCarouselPageCount(Page page) {
        return adCarouselMapper.getCarouselPageCount(page);
    }


}
