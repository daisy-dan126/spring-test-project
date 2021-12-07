package com.shenque.service;


import com.shenque.entity.SysUser;
import org.springframework.stereotype.Component;
@Component
public interface ISysUserService {

    SysUser findById(Long id);

    int addUser(SysUser user);

    int update(SysUser user);

    void affairAddUser() throws Exception;

    void affairAddHbase() throws Exception;
}
