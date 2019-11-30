package com.xjst.service.impl;

import com.xjst.dao.User;
import com.xjst.mapper.UserMapper;
import com.xjst.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private UserMapper userMapper;
    public List<User> getName() {
        return userMapper.listUser();
    }
}
