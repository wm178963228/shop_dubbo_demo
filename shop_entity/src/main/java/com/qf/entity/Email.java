package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 王铭
 * @Date 2019/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable{

    private String to;//发送给谁
    private String subject;//标题
    private String content;//内容


}
