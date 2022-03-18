package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.Workorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
@Repository
public interface WorkorderMapper extends BaseMapper<ItemInfo> {
@Select("select * from WORKORDER where ProductId=#{productId}")
List<Workorder> queryAllWorkorder(String productId);



}