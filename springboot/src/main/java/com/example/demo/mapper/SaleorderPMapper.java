package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ItemInfo;
import com.example.demo.entity.SaleorderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Component
public interface SaleorderPMapper extends BaseMapper<ItemInfo> {
    @Select("select distinct a.Product_id,b.Product_name from SbSaleorderDetaii a,JcProductInfoTab b where a.Product_id=b.Product_id")
    List<SaleorderDetail> queryAllSaleorderDetail();
}