package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.User1;


import java.util.List;

public interface UserService extends IService<User1> {


    List<User1> selectUserList();

 }

