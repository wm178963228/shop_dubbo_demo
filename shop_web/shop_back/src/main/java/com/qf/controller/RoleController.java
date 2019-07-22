package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Role;
import com.qf.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/2
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Reference
    private IRoleService roleService;


    /**
     * 角色列表
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public  String roleList(Model model){
        List<Role> roles = roleService.roleList();
        model.addAttribute("roles",roles);
        return "rolelist";
    }

    /**
     * 根据ajax的方式查询所有的角色
     * @param uid
     * @return
     */
    @RequestMapping("/listajax")
    @ResponseBody
    public List<Role> roleListAjax(Integer uid){
        List<Role> roles=roleService.roleListByUid(uid);
        return roles;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/insert")
    public  String roleInsert(Role role){
        roleService.insertRole(role);
        return "redirect:/role/list";
    }

    /**
     * 修改角色拥有的权限
     * @param rid
     * @param pids
     * @return
     */
    @RequestMapping("/updatePower")
    @ResponseBody
    public String updatePower(Integer rid, @RequestParam("pids[]") Integer[] pids){
        roleService.updateRolePowers(rid, pids);
        return "succ";
    }

}
