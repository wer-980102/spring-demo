package com.wer.order.service.mq;

import com.google.gson.Gson;
import com.wer.order.model.ShopOrder;
import com.wer.order.service.OrderService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${mq.delay.topic}",consumerGroup = "${mq.delay.consumer.group.name}",messageModel = MessageModel.CLUSTERING)
public class DelayOrderListener implements RocketMQListener<MessageExt> {

    @Autowired
    private OrderService orderService;

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            //1.解析消息内容
            String body = new String(messageExt.getBody(),"UTF-8");
            //TODO 使用GSON反序列化
            Gson gson = new Gson();
            ShopOrder order = (ShopOrder)gson.fromJson(body, ShopOrder.class);
            orderService.dealDealyOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
