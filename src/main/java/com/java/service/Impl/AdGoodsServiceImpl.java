package com.java.service.Impl;

import com.java.mapper.AdGoodsMapper;
import com.java.mapper.AdminMapper;
import com.java.po.Admin;
import com.java.po.Goods;
import com.java.po.Page;
import com.java.po.User;
import com.java.service.AdGoodsService;
import com.java.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adGoodsService")
public class AdGoodsServiceImpl implements AdGoodsService {

    @Autowired
    private AdGoodsMapper adGoodsMapper;

    @Override
    public List<Goods> getGoodsPageList(Page page) {
        return adGoodsMapper.getGoodsPageList(page);
    }

    @Override
    public int delGoods(Integer goodsId) {
        adGoodsMapper.delOrderByGoodsId(goodsId);
        adGoodsMapper.delCartByGoodsId(goodsId);
        adGoodsMapper.delCollectByGoodsId(goodsId);
        return adGoodsMapper.delGoodsById(goodsId);
    }

    @Override
    public int getGoodsPageCount(Page page) {
        return adGoodsMapper.getGoodsPageCount(page);
    }


}
