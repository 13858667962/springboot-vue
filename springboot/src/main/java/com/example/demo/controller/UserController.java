package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.User1;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/user") //前端调用
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "20") Integer pageSize,
                          @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User1> wrapper= Wrappers.<User1>lambdaQuery();

        if(StrUtil.isNotBlank(search))
        {
            wrapper.like(User1::getName,search);
        }
      Page<User1> userPage=userMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
      return Result.success(userPage);


        /*List<User1> userList=userService.selectUserList();
        return Result.success(userList);*/
    }
    @PostMapping("/add")
    public Result<?> save(@RequestBody User1 user) {
        userService.save(user);
        return Result.success();
    }

    @PutMapping("/add")
    public Result<?> update(@RequestBody User1 user) {
        userService.updateById(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable long id) {
        userMapper.deleteById(id);
        return Result.success();
    }


    @PostMapping("/login")
    public Result<?> login(@RequestBody User1 user) {
       User1 list=userMapper.selectOne(Wrappers.<User1>lambdaQuery().eq(User1::getName,user.getName()).eq(User1::getPassword,user.getPassword()));
        if(list==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success();
    }

}
