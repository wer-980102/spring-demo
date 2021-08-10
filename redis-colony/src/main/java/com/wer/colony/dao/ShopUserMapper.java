package com.wer.colony.dao;

import com.wer.colony.model.ShopUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopUserMapper {

    int deleteByPrimaryKey(Long userId);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    List<ShopUser> getUserList();

    ShopUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);
}
