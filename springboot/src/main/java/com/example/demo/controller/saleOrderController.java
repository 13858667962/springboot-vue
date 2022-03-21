package com.example.demo.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.saleOrder;
import com.example.demo.mapper.SaleorderDetailMapper;
import com.example.demo.mapper.SaleorderMainMapper;
import com.example.demo.mapper.saleOrderMapper;
import com.example.demo.service.saleOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/order")
public class saleOrderController {
    @Autowired
    saleOrderServiceImpl saleOrderService;
    @Resource
    saleOrderMapper saleOrderMapper;
    @Resource
    SaleorderMainMapper saleorderMainMapper;
    @Resource
    SaleorderDetailMapper saleorderDetailMapper;

    //该方法无用
    @PostMapping
    public Result<?> save(@RequestBody() saleOrder order) {
        saleOrder res = saleOrderMapper.selectOne(Wrappers.<saleOrder>lambdaQuery().eq(saleOrder::getSaleOrderCode, order.getSaleOrderCode()));
        if (res != null) {
            return Result.error("-1", "销售订单号重复");
        }
        return Result.success();
    }

    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize,
                          @RequestParam(defaultValue = "") String input) {
        LambdaQueryWrapper<saleOrder> wrapper = Wrappers.<saleOrder>lambdaQuery();
        if (StrUtil.isNotBlank(input)) {
            wrapper.like(saleOrder::getSaleOrderCode, input);
        }
        Page<saleOrder> saleOrderPage = saleOrderMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(saleOrderPage);
    }


    //查询数据库所有
    @GetMapping(value = "/query")
    public Result<?> get() {
        List<saleOrder> list = saleOrderMapper.queryAllUser();
        return Result.success(list);
    }

    //随机生成订单号，并将订单插入数据库
    @PostMapping(value = "/insert")
    public Result<?> insert(@RequestBody saleOrder order) {
       /* saleOrder res = saleOrderMapper.selectById(order.getSaleOrderCode());
        if(res!=null){
            return Result.error("-1","已存在订单号相同的订单");
        }else{
            saleOrderMapper.insert(order);
            return Result.success();
        }*/
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        int num = (1000 + new Random().nextInt(9000));
        String str = (df.format(date) + num);
        order.setSaleOrderCode(str);
        saleOrder res = saleOrderMapper.selectById(order.getSaleOrderCode());
        if (res != null) {
            return Result.error("-1", "随机数重复，请再次尝试添加");
        } else {
            saleOrderMapper.insert(order);
            return Result.success();
        }
    }

    //批量删除订单
    @PostMapping("/delete")
    public Result<?> delete(@RequestBody List<String> order) {
        saleOrderMapper.deleteBatchIds(order);
        return Result.success();
    }

    //按销售订单号查询
    @GetMapping("/selectById/{input}")
    public Result<?> selectById(@PathVariable("input") String input) {
        saleOrder res = saleOrderMapper.selectOne(Wrappers.<saleOrder>lambdaQuery().eq(saleOrder::getSaleOrderCode, input));
        if (res != null) {
            List<saleOrder> list = new ArrayList<>();
            list.add(res);
            return Result.success(list);
        } else {
            return Result.error("-1", "销售订单号不存在");
        }
    }

    //按订单号编辑
    @PutMapping("/editById")
    public Result<?> editById(@RequestBody saleOrder order) {
        saleOrderMapper.updateById(order);
        return Result.success();

    }

    //查询销售订单总条数
    @GetMapping("/number")
    public Result<?> getNumber(){
        int count = saleOrderService.count();
        return Result.success(count);
    }
}
