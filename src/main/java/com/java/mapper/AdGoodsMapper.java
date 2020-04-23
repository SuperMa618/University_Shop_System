package com.java.mapper;

import com.java.po.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdGoodsMapper {


	List<Goods> getGoodsPageList(Page page);

	int getGoodsPageCount(Page page);

	int delGoods(@Param("goodsId") Integer goodsId);

	int delGoodsById(@Param("goodsId") Integer goodsId);

	void delOrderByGoodsId(@Param("goodsId") Integer goodsId);

	void delCartByGoodsId(@Param("goodsId") Integer goodsId);

	void delCollectByGoodsId(@Param("goodsId") Integer goodsId);

    int getGoodsReviewPageCount(Page page);

	List<GoodsReview> getGoodsReviewPageList(Page page);

	int changeReviewStateById(@Param("goodsId") int goodsId);

	void goodsPublish(@Param("goods") Goods goods);

	void delReviewById(@Param("id") String id);
}
