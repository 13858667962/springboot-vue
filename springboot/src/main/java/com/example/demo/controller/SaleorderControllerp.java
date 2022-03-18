package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.SaleorderDetail;
import com.example.demo.entity.User1;
import com.example.demo.mapper.ItemInfoMapper;
import com.example.demo.mapper.SaleorderPMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/sp") //前端调用
public class SaleorderControllerp {


    @Autowired
    private SaleorderPMapper saleorderPMapper;


    @GetMapping("/list")
    public Result<?> list() {
        List<SaleorderDetail> list=saleorderPMapper.queryAllSaleorderDetail();
        return Result.success(list);
    }


    /*@GetMapping("/list")
    public Result<?> list() {
      List<ItemInfo> list=itemInfoMapper.queryAllItemInfo();
        return Result.success(list);
    }*/


   /* @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "200") Integer pageSize,@RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<ItemInfo> wrapper= Wrappers.<ItemInfo>lambdaQuery();

        if(StrUtil.isNotBlank(search))
        {
            wrapper.like(ItemInfo::getItemId,search);
        }
        Page<ItemInfo> userPage=itemInfoMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);



    }*/

      /*List<ItemInfo> list=itemInfoMapper.queryAllItemInfo();
      System.out.println(list);
        return Result.success(list);*/




    }
