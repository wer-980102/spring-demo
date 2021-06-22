package com.wer.user.dao;

import com.wer.user.model.ShopUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    ShopUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);

    int useCoupon(ShopUser record);
}
