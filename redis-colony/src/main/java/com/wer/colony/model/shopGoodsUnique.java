package com.wer.colony.model;

public class shopGoodsUnique {

    private Long orderId;

    public shopGoodsUnique(Long orderId) {
        this.orderId = orderId;
    }

    public shopGoodsUnique() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
