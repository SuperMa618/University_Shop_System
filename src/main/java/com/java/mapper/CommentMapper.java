package com.java.mapper;

import com.java.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {


    List<Comment> goodsShowComment(Map<String, Object> maps);

    int commit(@Param("comment") Comment comment);

    int buyCount(int id);

    int comCount(int id);

    int selectBCommentPageCount(Page page);

    List<Comment> selectBCommentPageList(Page page);

    List<Goods> findGoodsForComment(Map<String, Object> maps);

    int delComment(Map<String, Object> maps);

    List<CommentView> selectSCommentPageList(Page page);

    int selectSCommentPageCount(Page page);
}
