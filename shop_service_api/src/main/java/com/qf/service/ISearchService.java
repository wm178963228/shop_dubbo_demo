package com.qf.service;

import com.qf.entity.Goods;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/11
 */
public interface ISearchService {

    List<Goods> searchByKey(String keyword);

    int addGoods(Goods goods);

}
