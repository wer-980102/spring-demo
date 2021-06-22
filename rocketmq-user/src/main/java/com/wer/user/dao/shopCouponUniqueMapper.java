package com.wer.user.dao;

import com.wer.user.model.shopCouponUnique;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface shopCouponUniqueMapper {

    int insert(shopCouponUnique record);

    int insertSelective(shopCouponUnique record);
}
