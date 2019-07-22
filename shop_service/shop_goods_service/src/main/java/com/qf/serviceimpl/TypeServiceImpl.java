package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.TypeMapper;
import com.qf.entity.Type;
import com.qf.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/5
 */
@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> typeList() {
        return typeMapper.queryAllTypes();
    }

    @Override
    public int insertType(Type type) {
        return typeMapper.insert(type);
    }
}
