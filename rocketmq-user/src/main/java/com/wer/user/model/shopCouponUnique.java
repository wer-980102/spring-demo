package com.wer.user.model;

public class shopCouponUnique {
    private Long orderId;

    public shopCouponUnique(Long orderId) {
        this.orderId = orderId;
    }

    public shopCouponUnique() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
