package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.SaleorderDetail;
import com.example.demo.entity.User1;
import com.example.demo.entity.Workorder;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.WorkorderMapper;
import com.example.demo.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/workorder") //前端调用
public class WorkorderController {

    @Autowired
    private WorkorderMapper workorderMapper;

    @GetMapping("/list/{productId}")
    public Result<?> list(@PathVariable String productId) {
        System.out.println(productId);
        System.out.println("lalalalallalalalalalalalalalalalalalalalalal");
        List<Workorder> list=workorderMapper.queryAllWorkorder(productId);
        System.out.println(list);
        return Result.success(list);
    }


}
