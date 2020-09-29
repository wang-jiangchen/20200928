package com.tjetc.service;

import com.tjetc.domain.Goods;

import java.util.List;

public interface GoodsService {
    boolean addGoods(Goods goods);
    List<Goods> findAllGoods();
}
