package com.wer.server.service;

import com.google.gson.Gson;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ServerServerImpl {

    private static final Logger logger = LoggerFactory.getLogger(ServerServerImpl.class);


    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value("${mq.order.topic}")
    private String topic;

    public String getServiceInfo(String message){
        try {
            Gson gson = new Gson();
            String mes = gson.toJson(message);
            Message mess = new Message(topic, "", message, mes.getBytes());
            SendResult send = rocketMQTemplate.getProducer().send(mess);
            if(send.getSendStatus() == SendStatus.SEND_OK){
                logger.error("MQ消费消息成功：[" + message + "]");
                return  "服务端消息消费成功！";
            }else{
                logger.error("MQ发送消息失败：[" + message + "]");
                return  "服务端消息消费失败！";
            }
        }catch (Exception e){
            return "服务端报错啦！";
        }
    }
}
