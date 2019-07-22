package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.BackUser;
import com.qf.service.IBackUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/1
 */
@Controller
@RequestMapping("/buser")
public class BackUserController {

    @Reference
    private IBackUserService iBackUserService;

    /**
     * 查询后台用户的列表数据
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String userList(Model model){
        //调用后台服务，查询列表
        List<BackUser> backUsers = iBackUserService.queryAll();
        model.addAttribute("users",backUsers);
        return "buserlist";
    }


    /**
     * 添加用户
     * @param backUser
     * @return
     */
    @RequestMapping("/insert")
    public String addUser(BackUser backUser){
        iBackUserService.insertUser(backUser);
        return "redirect:/buser/list";
    }

    /**
     * 修改职工的所属角色
     * @return
     */
    @RequestMapping("/updaterole")
    public String updateUserRole(Integer uid,Integer[] rid){
        iBackUserService.updateUserRoles(uid,rid);
        return  "redirect:/buser/list";
    }
}
