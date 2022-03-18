package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.saleOrder;

import com.example.demo.mapper.saleOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class saleOrderServiceImpl extends ServiceImpl<saleOrderMapper, saleOrder> implements saleOrderService{
    @Autowired
    private saleOrderMapper saleOrderMapper;


}
