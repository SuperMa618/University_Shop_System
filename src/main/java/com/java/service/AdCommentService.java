package com.java.service;

import com.java.po.Comment;
import com.java.po.CommentView;
import com.java.po.Goods;
import com.java.po.Page;

import java.util.List;

public interface AdCommentService {


    List<CommentView> getCommentPageList(Page page);

    int getCommentPageCount(Page page);

    int delCommentById(Integer commentId);
}
