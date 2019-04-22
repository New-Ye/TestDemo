package com.songtian.mapper;

import com.songtian.entity.User;

import java.util.List;


public interface UserMapper {
    User selectByName(String name);//登录

    Integer register(User user);//注册

    List<User> selectAllUser();//查询所有人

    Integer deleteByName(String name);//根据姓名删除用户

    Integer updateByName(User user);//根据姓名更新用户信息

    List<User>  selectFeaturesBySlaveAndUser(String name);//三表联查，未完成

    List<User> slelectByBlurry(String name);//名字模糊查询


}
