package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 王铭
 * @Date 2019/7/19
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int register(User user) {
        return 0;
    }

    @Override
    public User queryByUserName(String username) {
        return null;
    }

    @Override
    public int updatePassword(String username, String password) {
        return 0;
    }

    @Override
    public User login(User user) {
        return null;
    }
}
