package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Email;
import com.qf.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 王铭
 * @Date 2019/7/18
 */
@Controller
@RequestMapping("sso")
public class SSOController {

    @Reference
    private IUserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/toregister")
    public String  toRegister(){
        return "register";
    }

    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toForgetPassword")
    public String toForgetPassword(){
        return "forgetPassword";
    }

    @RequestMapping("/sendCode")
    @ResponseBody
    public  String sendCode(String email){
        //设置邮件的内容
        String content="注册的验证码为：%d, 如果不是本人操作，请忽略！";
        //生成验证码
        int code = (int) ((Math.random()*9000)+1000);
        String.format(content,code);

        //发送邮件 - 封装邮件的实体类
        Email emailObj = new Email(email, "掏奋网注册验证码", content);

        //将验证码信息放入到redis中
        redisTemplate.opsForValue().set(email+"code",code);

        //将邮件放入rabbitmq
        rabbitTemplate.convertAndSend("email_exchange","",emailObj);
        return "succ";
    }



}
