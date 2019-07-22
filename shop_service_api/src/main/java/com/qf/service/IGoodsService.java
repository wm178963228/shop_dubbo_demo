package com.qf.service;

import com.qf.entity.BackUser;
import com.qf.entity.Goods;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/5
 */
public interface IGoodsService {
    List<Goods> queryAll();

    Goods insertGoods(Goods goods);

    Goods queryById(int id);

}
