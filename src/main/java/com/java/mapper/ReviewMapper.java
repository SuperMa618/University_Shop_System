package com.java.mapper;

import com.java.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReviewMapper {

    int delReview(@Param("id") String id);

    List<GoodsReview> getReviewPageList(Page page);

    int getReviewPageCount(Page page);
}
