package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.User1;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
@Repository
public interface ItemInfoMapper extends BaseMapper<ItemInfo> {
@Select("select * from JcItemInfoTab")
List<ItemInfo> queryAllItemInfo();



}