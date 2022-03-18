package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//@TableName("user1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User1 {
   @TableId(value="id",type = IdType.AUTO)
   private int id;
   private String name;
   private String address;
   private String age;
   private String sex;
   private String phone;
   private String password;
}
