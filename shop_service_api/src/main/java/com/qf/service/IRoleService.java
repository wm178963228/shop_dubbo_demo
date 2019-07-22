package com.qf.service;

import com.qf.entity.Role;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/1
 */
public interface IRoleService {

    List<Role> roleList();

    int insertRole(Role role);

    List<Role> roleListByUid(Integer uid);

    int updateRolePowers(Integer rid,Integer[] pids);
}
