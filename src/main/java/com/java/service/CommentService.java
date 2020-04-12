package com.java.service;

import com.java.po.*;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<Comment> goodsShowComment(Map<String, Object> maps);

    int commit(Comment comment);

    boolean compare(int id);

    List<Comment> selectBCommentPageList(Page page);

    int selectBCommentPageCount(Page page);

    List<Goods> findGoodsForComment(Map<String, Object> maps);

    int delComment(Map<String, Object> maps);

    List<CommentView> selectSCommentPageList(Page page);

    int selectSCommentPageCount(Page page);
}
