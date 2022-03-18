package com.example.demo.controller;

import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.common.Result;
import com.example.demo.entity.SaleorderDetail;
import com.example.demo.entity.saleOrder;
import com.example.demo.mapper.SaleorderDetailMapper;
import com.example.demo.mapper.SaleorderMainMapper;
import com.example.demo.mapper.saleOrderMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/order")
public class saleOrderController {
    @Resource
    saleOrderMapper saleOrderMapper;
    @Resource
    SaleorderMainMapper saleorderMainMapper;
    @Resource
    SaleorderDetailMapper saleorderDetailMapper;

    @PostMapping
    public Result<?> save(@RequestBody() saleOrder order){
        saleOrder res = saleOrderMapper.selectOne(Wrappers.<saleOrder>lambdaQuery().eq(saleOrder::getSaleOrderCode, order.getSaleOrderCode()));
        if(res!=null){
            return Result.error("-1","销售订单号重复");
        }
        saleOrderMapper.insert(order);
        return Result.success();
    }

    @PostMapping(value = "/query")
    public Result<?> get(){
        List<saleOrder> list = saleOrderMapper.queryAllUser();
        return Result.success(list);
    }

    @PostMapping(value = "/insert")
    public Result<?> insert(@RequestBody saleOrder order){
        saleOrder res = saleOrderMapper.selectById(order.getSaleOrderCode());
        if(res!=null){
            return Result.error("-1","已存在订单号相同的订单");
        }else{
            saleOrderMapper.insert(order);
            return Result.success();
        }

    }

    @PostMapping("/delete")
    public Result<?> delete(@RequestBody List<Integer> order){
        saleOrderMapper.deleteBatchIds(order);
        return Result.success();
    }

    @GetMapping("/selectById/{input}")
    public Result<?> selectById(@PathVariable("input") Integer input){
        saleOrder res = saleOrderMapper.selectOne(Wrappers.<saleOrder>lambdaQuery().eq(saleOrder::getSaleOrderCode, input));
        if(res!=null){
            List<saleOrder> list=new ArrayList<>();
            list.add(res);
            return Result.success(list);
        }else {
            return Result.error("-1","销售订单号不存在");
        }
    }

    @PutMapping("/editById")
    public Result<?> editById(@RequestBody saleOrder order){
        saleOrderMapper.updateById(order);
        return Result.success();

    }
}
