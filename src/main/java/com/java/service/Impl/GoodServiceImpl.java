package com.java.service.Impl;

import com.java.mapper.GoodsMapper;
import com.java.po.Goods;
import com.java.po.Page;
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
    public void goodsCollect(Map<String, Object> maps) {
        goodsMapper.goodsCollect(maps);
    }

    @Override
    public int delCart(Map<String, Object> maps) {
        return goodsMapper.delCart(maps);
    }

    @Override
    public int delCollect(Map<String, Object> maps) {
        return goodsMapper.delCollect(maps);
    }

    @Override
    public int selectPageCount(Page page) {
        return goodsMapper.selectPageCount(page);
    }

    @Override
    public List<Goods> selectPageList(Page page) {
        return goodsMapper.selectPageList(page);
    }

    @Override
    public void goodsBuy(Map<String, Object> maps) {
        goodsMapper.goodsBuy(maps);
    }

    @Override
    public Integer isGoodsCart(Map<String, Object> maps) {
        return goodsMapper.isGoodsCart(maps);
    }

    @Override
    public Integer isGoodsCollect(Map<String, Object> maps) {
        return goodsMapper.isGoodsCollect(maps);
    }

    @Override
    public void goodsCart(Map<String, Object> maps) {
        goodsMapper.goodsCart(maps);
    }

    @Override
    public List<Goods> findGoods(Map map) {
        return goodsMapper.findGoods(map);
    }
}
