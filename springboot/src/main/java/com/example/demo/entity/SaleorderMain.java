package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*销售订单明细*/


@TableName("SbSaleorderMain")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleorderMain {
   private String Saleordercode;
   private String Saleordertype;
   private String Signdate;
   private String ClientId;
   private String SOmaker;
   private String SOauditor;
   private String SOapprover;


}




























