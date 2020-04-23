package com.java.mapper;

import com.java.po.Carousel;
import com.java.po.CommentView;
import com.java.po.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdCarouselMapper {


	List<Carousel> getCarouselPageList(Page page);

	int getCarouselPageCount(Page page);

	int delCarouselById(@Param("id") Integer id);

	int insertCarousel(@Param("carousel") Carousel carousel);

	int changeState(Map<String, String> data);
}
