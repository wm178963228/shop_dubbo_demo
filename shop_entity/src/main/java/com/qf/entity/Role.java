package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王铭
 * @Date 2019/7/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String rolename;
    private String rolealias;
    private Date createtime;
    private Integer status;

    @TableField(exist = false)
    private Boolean checked;


}
