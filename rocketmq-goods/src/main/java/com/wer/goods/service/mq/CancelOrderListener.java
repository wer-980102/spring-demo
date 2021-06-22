package com.wer.goods.service.mq;

import com.google.gson.Gson;
import com.wer.goods.model.ShopOrder;
import com.wer.goods.service.GoodsService;
import com.wer.goods.service.GoodsServiceImpl;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "${mq.order.cancel.topic}",consumerGroup = "${mq.order.cancel.consumer.group.name}",messageModel = MessageModel.CLUSTERING)
public class CancelOrderListener implements RocketMQListener<MessageExt> {

    private static final Logger logger = LoggerFactory.getLogger(CancelOrderListener.class);

    @Autowired
    private GoodsService goodsService;

    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            //1.解析消息内容
            String body = new String(messageExt.getBody(),"UTF-8");
            //TODO 使用GSON反序列化
            Gson gson = new Gson();
            ShopOrder order = gson.fromJson(body, ShopOrder.class);
            long orderId =order.getOrderId();
            long goodsId =order.getGoodsId();
            Integer goodsNumber=order.getGoodsNumber();
            goodsService.CancelupdateGoods(orderId,goodsId,goodsNumber);
        } catch (Exception e) {
            logger.error("订阅消息：${mq.order.cancel.topic} 失败：[" + messageExt.getBody().toString() + "]");
            e.printStackTrace();
        }
    }
}
