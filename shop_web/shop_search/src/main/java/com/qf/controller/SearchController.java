package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 王铭
 * @Date 2019/7/11
 */
@Controller
@RequestMapping("/search")
public class SearchController {


    @Reference
    private ISearchService searchService;
    /**
     * 根据关键字搜索
     * @param keyword
     * @return
     */
    @RequestMapping("/searchByKey")
    public  String searchByKey(String keyword, Model model){
        System.out.println("获得搜索框输入的搜索关键字:" + keyword);

        List<Goods> goodsList = searchService.searchByKey(keyword);
        model.addAttribute("goodsList", goodsList);
        return "searchlist";
    }


}
