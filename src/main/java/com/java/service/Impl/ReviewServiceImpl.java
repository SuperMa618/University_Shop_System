package com.java.service.Impl;

import com.java.mapper.CommentMapper;
import com.java.mapper.ReviewMapper;
import com.java.po.*;
import com.java.service.CommentService;
import com.java.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<GoodsReview> getReviewPageList(Page page) {
        return reviewMapper.getReviewPageList(page);
    }

    @Override
    public int getReviewPageCount(Page page) {
        return reviewMapper.getReviewPageCount(page);
    }

    @Override
    public int delReview(String id) {
        return reviewMapper.delReview(id);
    }
}
