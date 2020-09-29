package com.tjetc.dao;

import com.tjetc.domain.Goods;

import java.util.List;

public interface GoodsDao {
    boolean addGoods(Goods goods);
    List<Goods> findAllGoods();
}
