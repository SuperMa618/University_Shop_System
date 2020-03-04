package com.java.service.Impl;

import com.java.mapper.GoodsMapper;
import com.java.po.Goods;
import com.java.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("goodService")
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public void saveGoods(Goods goods) {
        goodsMapper.saveGoods(goods);
    }

    @Override
    public List<Goods> findGoods(Map map) {
        return goodsMapper.findGoods(map);
    }
}
