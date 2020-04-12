package com.java.service.Impl;

import com.java.mapper.GoodsMapper;
import com.java.po.Comment;
import com.java.po.Goods;
import com.java.po.Orders;
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
    public List<Goods> selectCartPageList(Page page) {
        return goodsMapper.selectCartPageList(page);
    }

    @Override
    public void goodsBuy(Map<String, Object> maps) {
        //加入订单
        goodsMapper.goodsBuy(maps);
        //改变商品状态
        goodsMapper.changeGoodsStateOne(maps.get("goodsId").toString());
    }

    @Override
    public int compOrders(Map<String, Object> maps) {
        goodsMapper.changeGoodsStateTwo(maps.get("goodsId").toString());
        return goodsMapper.compOrders(maps);
    }

    @Override
    public void batchBuy(Map<String, Object> maps) {
        //加入订单
        goodsMapper.goodsBuy(maps);
        //改变商品状态
        goodsMapper.changeGoodsStateOne(maps.get("goodsId").toString());
        //下单后删除购物车相应的商品
        goodsMapper.delCart(maps);
    }

    @Override
    public int delOrdersByBuyer(Map<String, Object> maps) {
        goodsMapper.changeGoodsStateZero(maps.get("goodsId").toString());
        return goodsMapper.delOrdersByBuyer(maps);
    }

    //用户查看自己已发布的商品
    @Override
    public List<Orders> selectPublishPageList(Page page) {
        return goodsMapper.selectPublishPageList(page);
    }



    @Override
    public int delPublish(Map<String, Object> maps) {
        return goodsMapper.delPublish(maps);
    }

    //用户查看自己已发布的商品
    @Override
    public int selectPublishPageCount(Page page) {
        return goodsMapper.selectPublishPageCount(page);
    }

    @Override
    public int selectHistoryPageCount(Page page) {
        return goodsMapper.selectHistoryPageCount(page);
    }

    @Override
    public List<Orders> selectHistoryPageList(Page page) {
        return goodsMapper.selectHistoryPageList(page);
    }

    @Override
    public int delOrdersBySeller(Map<String, Object> maps) {
        goodsMapper.changeGoodsStateZero(maps.get("goodsId").toString());
        return goodsMapper.delOrdersBySeller(maps);
    }

    @Override
    public int selectSellPageCount(Page page) {
        return goodsMapper.selectSellPageCount(page);
    }

    @Override
    public List<Orders> selectSellPageList(Page page) {
        return goodsMapper.selectSellPageList(page);
    }

    @Override
    public Integer isGoodsOrders(Map<String, Object> maps) {
        return goodsMapper.isGoodsOrders(maps);
    }

    @Override
    public int selectOrdersPageCount(Page page) {
        return goodsMapper.selectOrdersPageCount(page);
    }

    @Override
    public List<Orders> selectOrdersPageList(Page page) {
        return goodsMapper.selectOrdersPageList(page);
    }

    @Override
    public int selectCartPageCount(Page page) {
        return goodsMapper.selectCartPageCount(page);
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
