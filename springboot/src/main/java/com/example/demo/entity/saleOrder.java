package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@TableName("saleOrder")
@Data
//销售订单
public class saleOrder {
    @TableId()
    private String saleOrderCode;

    private String saleOrderType;
    private String signDate;
    private String clientId;
    private String soMaker;
    private String soAuditor;
    private String soApprover;
    private String productId;
    private String deliveryDate;
    private long soQuantity;
    private double price;
    private String soDescription;


}
