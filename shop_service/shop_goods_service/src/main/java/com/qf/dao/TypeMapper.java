package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Type;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/5
 */
public interface TypeMapper extends BaseMapper<Type> {
    List<Type> queryAllTypes();
}
