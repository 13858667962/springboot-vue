package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User1;
import com.example.demo.entity.saleOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface saleOrderMapper extends BaseMapper<saleOrder> {
    @Select("select * from saleOrder")
    List<saleOrder> queryAllUser();


}
