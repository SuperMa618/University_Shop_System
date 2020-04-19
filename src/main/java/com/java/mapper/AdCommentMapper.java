package com.java.mapper;

import com.java.po.Comment;
import com.java.po.CommentView;
import com.java.po.Goods;
import com.java.po.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdCommentMapper {


	List<CommentView> getCommentPageList(Page page);

	int getCommentPageCount(Page page);

	int delCommentById(@Param("commentId") Integer commentId);

}
