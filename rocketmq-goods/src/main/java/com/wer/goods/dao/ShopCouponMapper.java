package com.wer.goods.dao;

import com.wer.goods.model.ShopCoupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopCouponMapper {
    int deleteByPrimaryKey(Long couponId);

    int insert(ShopCoupon record);

    int insertSelective(ShopCoupon record);

    ShopCoupon selectByPrimaryKey(Long couponId);

    int updateByPrimaryKeySelective(ShopCoupon record);

    int updateByPrimaryKey(ShopCoupon record);
}
