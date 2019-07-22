package com.qf.service;

import com.qf.entity.Power;
import com.qf.entity.Role;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/2
 */
public interface IPowerService {
    List<Power> powerList();

    List<Power> powerListByRid(Integer rid);

    int insert(Power power);


}
