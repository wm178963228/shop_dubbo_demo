package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Power;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/2
 */
public interface PowerMapper extends BaseMapper<Power>{

    List<Power> queryAllPowers();

    List<Power> queryPowerByRid(Integer rid);
}
