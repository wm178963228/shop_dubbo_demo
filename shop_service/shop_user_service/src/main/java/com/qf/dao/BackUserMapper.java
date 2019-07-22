package com.qf.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.BackUser;

/**
 * @author 王铭
 * @Date 2019/7/1
 */
public interface BackUserMapper extends BaseMapper<BackUser>{

    BackUser queryByUserName(String username);

}
