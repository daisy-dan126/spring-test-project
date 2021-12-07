package com.shenque.dao;

/**
 * SysUserMapper
 */
import com.shenque.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper {

    SysUser findById(Long id);

    int addUser(SysUser user);

    int update(SysUser user);

}
