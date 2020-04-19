package com.java.mapper;

import com.java.po.Admin;
import com.java.po.Goods;
import com.java.po.Page;
import com.java.po.User;
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
}
