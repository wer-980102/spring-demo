package com.wer.order.service;

import com.wer.order.model.ShopOrder;

public interface OrderService {

    long submitOrder(ShopOrder shopOrder);

    int dealDealyOrder(ShopOrder shopOrder) throws Exception;

    int ConfirmOrder(long orderid) throws Exception;
}
