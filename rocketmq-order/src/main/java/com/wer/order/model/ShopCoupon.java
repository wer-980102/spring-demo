package com.wer.order.model;

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
public class ShopCoupon {
    private Long couponId;

    private BigDecimal couponPrice;

    private Integer isUsed;

    private Date usedTime;
    
}
