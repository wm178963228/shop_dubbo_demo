package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 王铭
 * @Date 2019/7/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleTable implements Serializable{

    private Integer uid;
    private Integer rid;
}
