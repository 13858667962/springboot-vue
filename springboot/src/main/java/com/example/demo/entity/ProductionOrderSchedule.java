package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("WORKORDER")
@Data
public class ProductionOrderSchedule {
    private String woordercode;
    private String productid;
    private Integer wocustomquantity;
    private Integer wocompletequantity;
    private Date womakedate;
    private Date wostartdate;
    private Date worealcompletedate;
    private Date woplancompletedate;
    private String wooperator;
    private Boolean wostatus;
    private String womprno;
    private String womscode;
    private String wowscode;
    private String wodescripyion;
}
