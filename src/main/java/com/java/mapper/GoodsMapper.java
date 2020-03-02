package com.java.mapper;

import com.java.po.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    //保存提交商品信息
    void saveGoods(@Param("goods") Goods goods);

    List<Goods> findAllGoods();
}
