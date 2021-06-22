package com.wer.order.service;

import com.google.gson.Gson;
import com.wer.order.dao.ShopOrderMapper;
import com.wer.order.model.ShopOrder;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * 订单info
 */
@Service
public class OrderServiceImpl implements OrderService{

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final String SUCCESS = "success";
    private static final String FAILUER = "failure";
    @Autowired
    private ShopOrderMapper shopOrderMapper;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderServiceImpl saveOrder;

    @Value("${mq.order.topic}")
    private String topic;

    @Value("${mq.order.cancel.topic}")
    private String canceltopic;

    /**
     * 用户购买商品-扣减库存-使用优惠券
     * @param shopOrder
     * @return
     */
    @Transactional
    @Override
    public long submitOrder(ShopOrder shopOrder){
       long orderId=0;
       try {
           shopOrderMapper.insert(shopOrder);

           if( shopOrder!=null && shopOrder.getOrderId()!=null){
                orderId = shopOrder.getOrderId();
           }
           if(orderId <= 0){
               return orderId;
           }
           //去调用商品系统（扣减库存）
          // restUpdateGoods(shopOrder);
           //去调用用户系统（处理优惠券）
          // restUseCoupon(shopOrder);

           //MQ-去调用商品系统（扣减库存）
           MQShopOrder(shopOrder);
           //MQ-去调用用户系统（处理优惠券）
           MQDelayOrder(shopOrder);
       }catch (Exception e){
           return -1;
       }
       return orderId;
    }

    /**
     * 请求商品接口
     * 购买商品
     * @param shopOrder
     * @return
     */
    public int restUpdateGoods(ShopOrder shopOrder){
        System.out.println("------------已购买商品！-------------");
        String url = "http://127.0.0.1:8089/updateGoods";
        url = url + "?goodsId=" + shopOrder.getGoodsId() + "&goodsNumber=" + shopOrder.getGoodsNumber();
        try {
            String str = restTemplate.getForEntity(url, String.class).getBody();
            restTemplate.delete(url);
            if (str.equals("success")) {
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e1){
            e1.printStackTrace();
            return -1;
        }

    }

    /**
     * 用户使用优惠券
     * @param shopOrder
     * @return
     */
    public int restUseCoupon(ShopOrder shopOrder) {
        System.out.println("------------已使用优惠券！-------------");
        String urlUser = "http://127.0.0.1:8099/useCoupon";
        urlUser = urlUser + "?couponId=" + shopOrder.getCouponId();
        try {
            String str = restTemplate.getForEntity(urlUser, String.class).getBody();
            restTemplate.delete(urlUser);
            if (str.equals("success")) {
                return 1;
            }else{
                return 0;
            }
        }catch (Exception e1){
            e1.printStackTrace();
            return -1;
        }
    }

    /**
     * 确认订单
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public int ConfirmOrder(long orderId) throws Exception{
        System.out.println("------------已购买商品！-------------");
        ShopOrder shopOrder = shopOrderMapper.selectByPrimaryKey(orderId);
        shopOrder.setOrderStatus(1);
        shopOrder.setPayStatus(2);
        if(  shopOrderMapper.updateByPrimaryKey(shopOrder)>0){
            return  1;
        }else{
            return  -1;
        }
    }
    /**
     * 生成订单
     * @param shopOrder
     * @return
     * @throws Exception
     */
    public int MQShopOrder(ShopOrder shopOrder)throws Exception{
        //TODO 使用Gson序列化
        Gson gson = new Gson();
        String mes = gson.toJson(shopOrder);
        Message message = new Message(topic, "", shopOrder.getOrderId().toString(), mes.getBytes());
        SendResult send = rocketMQTemplate.getProducer().send(message);
        if(send.getSendStatus() == SendStatus.SEND_OK){
            return  1;
        }else{
            logger.error("MQ发送消息失败：[" + shopOrder.getOrderId() + "]");
            return  -1;
        }
    }

    /**
     * 生成限时订单
     * @param shopOrder
     * @return
     * @throws Exception
     */
    public int MQDelayOrder(ShopOrder shopOrder) throws Exception {
        //TODO 使用Gson序列化
        Gson gson = new Gson();
        String txtMsg = gson.toJson(shopOrder);
        Message message = new Message("Wer_delayOrder","",shopOrder.getOrderId().toString(),txtMsg.getBytes());
        // delayTimeLevel：(1~18个等级)"1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h"
        message.setDelayTimeLevel(5);//1分钟不支付，就触发延时消息，就会把订单置为无效，还有回退。。。。
        SendResult sendResult = rocketMQTemplate.getProducer().send(message);
        if(sendResult.getSendStatus() == SendStatus.SEND_OK){
            return  1;
        }else{
            return  -1;
        }
    }

    /**
     * 超时订单处理
     * @param shopOrder
     * orderStatus:订单状态 0未确认 1已确认 2已取消 3无效 4退款
     * payStatus:支付状态 0未支付 1支付中 2已支付
     * @return
     * @throws Exception
     */
    @Override
    public int dealDealyOrder(ShopOrder shopOrder) throws Exception{
        ShopOrder shopOrderReal = shopOrderMapper.selectByPrimaryKey(shopOrder.getOrderId());
        if(null == shopOrderReal) return -1;
        if(shopOrderReal.getPayStatus() == 2
                ||shopOrderReal.getOrderStatus()==2
                ||shopOrderReal.getOrderStatus()==3
                ||shopOrderReal.getOrderStatus()==4){
            System.out.println("该订单已经付款!");
            return 1;
        }
        shopOrder.setOrderStatus(3);//订单状态（订单超时没有支付，支付失败） --3无效
        if(shopOrderMapper.updateByPrimaryKeySelective(shopOrderReal)>0){
            System.out.println("该订单已经超时，改为无效！");
            return  1;
        }else{
            return  -1;
        }
    }

}
