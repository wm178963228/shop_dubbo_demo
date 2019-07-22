package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Type;
import com.qf.service.ITypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/5
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    @Reference
    private ITypeService typeService;

    @RequestMapping("/list")
    public  String typelist(Model model){
        List<Type> types = typeService.typeList();
        model.addAttribute("types",types);
        return "typelist";
    }

    @RequestMapping("/insert")
    public String insertType(Type type){
        typeService.insertType(type);
        return "redirect:/type/list";
    }

    @RequestMapping("/listajax")
    @ResponseBody
    public List<Type> Typelistajax(){
        return typeService.typeList();
    }

    @ResponseBody
    @RequestMapping("/listType")
    public Object listType(){
        List<Type> goodsTypes = typeService.typeList();
        return goodsTypes;
    }
}
