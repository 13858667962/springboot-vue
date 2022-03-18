package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.ProductionOrderSchedule;
import com.example.demo.mapper.ProductionOrderScheduleMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

@RestController
@RequestMapping("/productionorderschedule")
public class ProductionOrderScheduleController {
    @Resource
    ProductionOrderScheduleMapper ProductionOrderScheduleMapper;
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue="1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize ,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<ProductionOrderSchedule> wrapper = Wrappers.<ProductionOrderSchedule>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(ProductionOrderSchedule::getWoordercode,search);
        }
        Page < ProductionOrderSchedule > ProductionOrderSchedulePage = ProductionOrderScheduleMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(ProductionOrderSchedulePage);
    }

}
