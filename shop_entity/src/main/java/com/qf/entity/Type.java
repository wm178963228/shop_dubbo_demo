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
 * @Date 2019/7/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer pid = -1;
    private String tname;
    private Date createtime = new Date();
    private Integer status;

    @TableField(exist = false)
    private String pname;

    @TableField(exist = false)
    private Boolean checked;


}
