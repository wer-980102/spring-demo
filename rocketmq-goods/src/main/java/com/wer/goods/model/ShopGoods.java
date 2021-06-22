package com.wer.goods.model;

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
public class ShopGoods {
    private Long goodsId;

    private String goodsName;

    private Integer goodsNumber;

    private BigDecimal goodsPrice;

    private String goodsDesc;

    private Date addTime;

}
