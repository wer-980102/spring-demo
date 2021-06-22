package com.wer.order.dao;

import com.wer.order.model.ShopOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(ShopOrder record);

    int insertOrder(ShopOrder record);

    int insertSelective(ShopOrder record);

    ShopOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);


    /*找出未支付且未过期的订单 */
    List<ShopOrder> selectUnPayOrders();
}
