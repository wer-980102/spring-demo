package com.wer.order.dao;

import com.wer.order.model.ShopUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    ShopUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);
}
