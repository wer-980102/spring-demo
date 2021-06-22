package com.wer.user.service.mq;


import com.google.gson.Gson;
import com.wer.user.model.ShopOrder;
import com.wer.user.service.UserService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


@Component
@RocketMQMessageListener(topic = "${mq.order.topic}",consumerGroup = "${mq.order.consumer.group.name}",messageModel = MessageModel.CLUSTERING)
public class UserListener implements RocketMQListener<MessageExt> {
    @Autowired
    private UserService userService;

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            //1.解析消息内容
            String body = new String(messageExt.getBody(),"UTF-8");
            //TODO 使用GSON反序列化
            Gson gson = new Gson();
            ShopOrder order = (ShopOrder)gson.fromJson(body, ShopOrder.class);
            userService.useCoupon(order.getOrderId(),order.getCouponId());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
