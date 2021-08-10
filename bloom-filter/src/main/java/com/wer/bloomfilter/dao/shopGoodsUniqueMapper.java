package com.wer.bloomfilter.dao;

import com.wer.bloomfilter.model.shopGoodsUnique;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface shopGoodsUniqueMapper {

    int insert(shopGoodsUnique record);

    int insertSelective(shopGoodsUnique record);
}
