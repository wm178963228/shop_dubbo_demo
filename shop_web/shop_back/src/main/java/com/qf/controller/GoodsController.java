package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.entity.Goods;
import com.qf.entity.Type;
import com.qf.service.IGoodsService;
import com.qf.service.ITypeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author 王铭
 * @Date 2019/7/6
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private IGoodsService goodsService;

    @Reference
    private ITypeService typeService;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${upload.path}")
    private String uploadPath;

    @RequestMapping("/list")
    public String goodsList(Model model){
        List<Goods> goods = goodsService.queryAll();
        model.addAttribute("goods",goods);
        return "goodslist";
    }

    @RequestMapping("/insert")
    public String insertGoods(Goods goods){
        goodsService.insertGoods(goods);
        return "redirect:/goods/list";
    }

    /**
     * 图片上传
     * 上传的图片存在哪
     * 放在tomcat上的话等再次启动tomcat时，之前的图片数据就会没有
     * 放在本地路径的客户端要如何取到
     * @return
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(MultipartFile file){
        String uploadFile = "";
        //截取图片的后缀
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String houzhui = originalFilename.substring(index + 1);
        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(
                    file.getInputStream(),
                    file.getSize(),
                    houzhui,
                    null
            );
            //获得上传的路径
            uploadFile = storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "{\"filepath\":\"" + uploadFile + "\"}";
    }

    /**
     * 获取服务器的图片
     */
    @RequestMapping("/getImg")
    public void getImg(String imgpath, HttpServletResponse response){
        //查询本地的文件
        File file = new File(imgpath);

        try (
                InputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream();
        ){

            IOUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/listajax")
    @ResponseBody
    public  List<Type> typeListAjax(){
        return typeService.typeList();
    }

    @RequestMapping("/tohtml")
    public Object to(){
        return "ueditor";
    }
}
