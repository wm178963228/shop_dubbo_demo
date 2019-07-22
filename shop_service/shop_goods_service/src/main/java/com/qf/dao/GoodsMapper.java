package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Goods;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/6
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> listGoods();
}
