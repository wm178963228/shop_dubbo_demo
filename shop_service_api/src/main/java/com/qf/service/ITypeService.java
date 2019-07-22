package com.qf.service;

import com.qf.entity.Type;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/5
 */
public interface ITypeService {

    List<Type> typeList();

    int insertType(Type type);
}
