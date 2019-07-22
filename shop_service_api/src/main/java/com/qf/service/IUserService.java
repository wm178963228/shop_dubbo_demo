package com.qf.service;


import com.qf.entity.User;

/**
 * @author 王铭
 * @Date 2019/7/19
 */
public interface IUserService {
    int register(User user);

    User queryByUserName(String username);

    int updatePassword(String username, String password);

    User login(User user);
}
