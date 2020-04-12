package com.java.service.Impl;

import com.java.mapper.CommentMapper;
import com.java.mapper.UserMapper;
import com.java.po.*;
import com.java.service.CommentService;
import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectBCommentPageList(Page page) {
        return commentMapper.selectBCommentPageList(page);
    }

    @Override
    public List<CommentView> selectSCommentPageList(Page page) {
        return commentMapper.selectSCommentPageList(page);
    }

    @Override
    public int selectSCommentPageCount(Page page) {
        return commentMapper.selectSCommentPageCount(page);
    }

    @Override
    public int delComment(Map<String, Object> maps) {
        return commentMapper.delComment(maps);
    }

    @Override
    public List<Goods> findGoodsForComment(Map<String, Object> maps) {
        return commentMapper.findGoodsForComment(maps);
    }

    @Override
    public int selectBCommentPageCount(Page page) {
        return commentMapper.selectBCommentPageCount(page);
    }

    @Override
    public boolean compare(int id) {
        //购买次数与留言次数
        int buycount = commentMapper.buyCount(id);
        int comcount = commentMapper.comCount(id);
        if (buycount >comcount) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int commit(Comment comment) {
        return commentMapper.commit(comment);
    }

    @Override
    public List<Comment> goodsShowComment(Map<String, Object> maps) {
        return commentMapper.goodsShowComment(maps);
    }
}
