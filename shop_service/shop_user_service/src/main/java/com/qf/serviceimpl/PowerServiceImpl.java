package com.qf.serviceimpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.PowerMapper;
import com.qf.dao.RoleMapper;
import com.qf.dao.RolePowerMapper;
import com.qf.entity.Power;
import com.qf.entity.Role;
import com.qf.service.IPowerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/2
 */
@Service
public class PowerServiceImpl implements IPowerService {

    @Autowired
    private PowerMapper powerMapper;


    @Override
    public List<Power> powerList() {
        return powerMapper.queryAllPowers();
    }

    @Override
    public List<Power> powerListByRid(Integer rid) {
        return powerMapper.queryPowerByRid(rid);
    }

    @Override
    public int insert(Power power) {
        return powerMapper.insert(power);
    }
}
