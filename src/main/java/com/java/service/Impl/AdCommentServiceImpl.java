package com.java.service.Impl;

import com.java.mapper.AdCommentMapper;
import com.java.mapper.AdGoodsMapper;
import com.java.po.Comment;
import com.java.po.CommentView;
import com.java.po.Goods;
import com.java.po.Page;
import com.java.service.AdCommentService;
import com.java.service.AdGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adCommentService")
public class AdCommentServiceImpl implements AdCommentService {

    @Autowired
    private AdCommentMapper adCommentMapper;

    @Override
    public List<CommentView> getCommentPageList(Page page) {
        return adCommentMapper.getCommentPageList(page);
    }

    @Override
    public int delCommentById(Integer goodsId) {
        return adCommentMapper.delCommentById(goodsId);
    }

    @Override
    public int getCommentPageCount(Page page) {
        return adCommentMapper.getCommentPageCount(page);
    }


}
