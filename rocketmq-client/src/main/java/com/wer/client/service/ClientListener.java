package com.wer.client.service;

import com.google.gson.Gson;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * 监听mq
 */
@Component
@RocketMQMessageListener(topic = "${mq.order.topic}",consumerGroup = "${mq.order.consumer.group.name}",messageModel = MessageModel.CLUSTERING)
public class ClientListener implements RocketMQListener<MessageExt> {

    private static final Logger logger = LoggerFactory.getLogger(ClientListener.class);

    @Autowired
    private ClientServiceImpl clientService;

    /**
     * 监听消息
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt){
        try {
            //1.解析消息内容
            String body = new String(messageExt.getBody(),"UTF-8");
            //TODO 使用GSON反序列化
            Gson  gson = new Gson();
            String mess = gson.fromJson(body, String.class);
            String clientInfo = clientService.getClientInfo(mess);
            logger.error("MQ发送消息成功：[" + clientInfo + "]");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
