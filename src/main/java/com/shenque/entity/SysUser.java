package com.shenque.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable{

    private static final long serialVersionUID = 1L;

    //主键 : id
    private Long id;

    //账号 : usercode
    private String usercode;

    //姓名 : username
    private String username;

    //密码 : password
    private String password;

    //盐 : salt
    private String salt;

    //账号是否锁定，1：锁定，0未锁定 : locked
    private String locked;

}

