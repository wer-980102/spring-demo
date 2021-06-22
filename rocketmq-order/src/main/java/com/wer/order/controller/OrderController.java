package com.wer.order.controller;

import com.wer.order.model.ShopOrder;
import com.wer.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class OrderController {

    private static final String SUCCESS = "success";
    private static final String FAILUER = "failure";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    /**
     * 提交订单
     * http://localhost:8080/submitOrder?userId=1&goodsId=13&goodsNumber=1&couponId=1
     * @param userId
     * @param goodsId
     * @param goodsNumber
     * @param couponId
     * @return
     */
    @RequestMapping("/submitOrder")
    public String submitOrder(@RequestParam("userId")long userId, @RequestParam("goodsId")long goodsId, @RequestParam("goodsNumber")int goodsNumber, @RequestParam("couponId")long couponId){
        long orderid ;
        try {
            //check() 略过
            //确认订单
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setUserId(userId);
            shopOrder.setGoodsId(goodsId);
            shopOrder.setGoodsNumber(goodsNumber);
            shopOrder.setCouponId(couponId);
            shopOrder.setOrderStatus(0);
            shopOrder.setPayStatus(1);
            shopOrder.setShippingStatus(0);
            shopOrder.setAddTime(new Date());
            orderid =orderService.submitOrder(shopOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return FAILUER;
        }
        if(orderid>=0){
            return SUCCESS+":"+orderid;
        }else{
            return FAILUER;
        }
    }

}
