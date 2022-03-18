package com.example.demo.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*产品信息表*/


@TableName("JcProductInfoTab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
   @TableId(value="Uuid")
   private String Uuid;
   private String ProductId;
   private int Version;
   private String BasicPicNo;
   private String ProductName;
   private String ProductGenre;
   private String ProductModel;
   private String ProductType;
   private String ProductClass;
   private String ProductApplication;
   private String ProductState;
   private String PtoType;
   private String InCylinderBrake;
   private String AirIntakeHeating;
   private String EddyRatio;
   private String HorsepowerType;
   private String Horsepower;
   private String Belt;
   private String Emission;
   private String DeliveryVolume;
   private String FuelPump;
   private String FuelInjector;
   private String Fuel;
   private String RatedPower;

}




























