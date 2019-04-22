package com.songtian.service.impl;


import com.songtian.entity.User;
import com.songtian.mapper.UserMapper;
import com.songtian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public Integer register(User user) {
        return userMapper.register(user);
    }

    @Override
    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public Integer deleteByName(String name) {
        return userMapper.deleteByName(name);
    }

    @Override
    public Integer updateByName(User user) {
        return userMapper.updateByName(user);
    }

    @Override
    public List<User> selectFeaturesBySlaveAndUser(String name) {
        return userMapper.selectFeaturesBySlaveAndUser(name);
    }

    @Override
    public List<User> slelectByBlurry(String name) {
        return userMapper.slelectByBlurry(name);
    }
}
