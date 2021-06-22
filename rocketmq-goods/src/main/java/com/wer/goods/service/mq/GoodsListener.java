package com.wer.goods.service.mq;

import com.google.gson.Gson;
import com.wer.goods.model.ShopOrder;
import com.wer.goods.service.GoodsService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * 监听mq
 */
@Component
@RocketMQMessageListener(topic = "${mq.order.topic}",consumerGroup = "${mq.order.consumer.group.name}",messageModel = MessageModel.CLUSTERING)
public class GoodsListener implements RocketMQListener<MessageExt> {
    @Autowired
    private GoodsService goodsService;

    /**
     * 监听消息
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            //1.解析消息内容
            String body = new String(messageExt.getBody(),"UTF-8");
            //TODO 使用GSON反序列化
            Gson  gson = new Gson();
            ShopOrder shopOrder = gson.fromJson(body, ShopOrder.class);
            Long orderId = shopOrder.getOrderId();
            Long goodsId = shopOrder.getGoodsId();
            Integer goodsNumber = shopOrder.getGoodsNumber();
            //2.扣减库存
            goodsService.updateGoods(orderId,goodsId,goodsNumber);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
