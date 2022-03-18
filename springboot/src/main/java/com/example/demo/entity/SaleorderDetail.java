package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/*销售订单明细*/


@TableName("SbSaleorderDetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleorderDetail {
   @TableId
   private String Saleordercode;
   private String ProductId;
   private Date DeliveryDate;
   private long SOquantity;
   private double Price;
   private String SODESCRIPYION;
   private String Guanlianquantity;



}




























