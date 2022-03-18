package com.example.demo.service;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User1;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User1> implements UserService {

    @Autowired
    private UserMapper userMapper;


    public List<User1> selectUserList() {
        return userMapper.queryAllUser();
    }


}


