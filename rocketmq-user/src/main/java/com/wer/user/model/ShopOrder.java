package com.wer.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrder {
    private Long orderId;

    private Long userId;

    private Integer orderStatus;

    private Integer payStatus;

    private Integer shippingStatus;

    private Long goodsId;

    private Integer goodsNumber;

    private Long couponId;

    private BigDecimal couponPaid;

    private Date addTime;

    private Date confirmTime;

    private Date payTime;

}
