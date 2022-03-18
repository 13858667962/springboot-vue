package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/*产品信息表*/


@TableName("WORKORDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workorder {
   @TableId(value="woordercode")
   private String woordercode;
   private String ProductId;
   private int wocustomquantity;
   private String wocompletequantity;
   private Date womakedate;
   private Date wostartdate;
   private Date worealcompletedate;
   private Date woplancompletedate;
   private String wooperator;
   private String wostatus;
   private String womprno;
   private String womscode;
   private String wowscode;
   private String WODESCRIPYION;
}




























