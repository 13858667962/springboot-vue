package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("JcItemInfoTab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemInfo {
   @TableId(value="Uuid")
   private String Uuid;
   private String ItemId;
   private String ItemName;
   private String ItemType;




}




























