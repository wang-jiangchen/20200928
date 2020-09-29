package com.tjetc.service.impl;

import com.tjetc.dao.GoodsDao;
import com.tjetc.dao.impl.GoodsDaoImpl;
import com.tjetc.domain.Goods;
import com.tjetc.service.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao=new GoodsDaoImpl();

    @Override
    public boolean addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsDao.findAllGoods();
    }
}
