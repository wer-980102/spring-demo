package com.wer.colony.dao;

import com.wer.colony.model.shopGoodsUnique;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface shopGoodsUniqueMapper {

    int insert(shopGoodsUnique record);

    int insertSelective(shopGoodsUnique record);
}
