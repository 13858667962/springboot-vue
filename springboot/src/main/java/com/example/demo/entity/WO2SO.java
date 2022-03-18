package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*产品信息表*/


@TableName("WORKORDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WO2SO {
   @TableId(value="woordercode")
   private String woordercode;
   private String Saleordercode;
   private String ProductId;
   private String wosoassignquantity;
}




























