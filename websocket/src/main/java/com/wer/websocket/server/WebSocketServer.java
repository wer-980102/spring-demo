package com.wer.websocket.server;

import com.alibaba.fastjson.JSONObject;
import com.wer.websocket.config.WebSocketConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
@ServerEndpoint("/websocket/{openid}")
public class WebSocketServer {

    /** 用来记录当前在线连接数 **/
    private static AtomicInteger atomicInteger = new AtomicInteger();
    /** 存放每个客服端对应的WebSocket对象 **/
    private static ConcurrentHashMap<String,Session> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * 发送消息
     * @param session
     * @param message
     * @throws IOException
     */
    public void  sendMessage(Session session,String message)throws IOException {
            if(null != session){
                synchronized (session){
                    log.info("锁消息，避免重复！");
                    log.info("发送消息：{}",message);
                    session.getBasicRemote().sendText(message.toString());
                }
            }
    }

    /**
     *  给指定的用户发送信息
     * @param openid
     * @param object
     */
    public void sendInfo(String openid, JSONObject object){
        //拿取个人信息
        Session session = concurrentHashMap.get(openid);
        log.info("给[{}], session:{}", openid, session);
        try{
            sendMessage(session,object.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 给指定用户发送信息
     * @param openid
     * @param message
     */
    public void sendInfo(String openid,String message){
        //拿取个人信息
        Session session = concurrentHashMap.get(openid);
        log.info("给[{}], session:{}", openid, message);
        try{
            sendMessage(session,message);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  建立连接，调用
     * @param session
     * @param openid
     */
    @OnOpen
    public void  onOpen(Session session,@PathParam(value = "openid") String openid){
        concurrentHashMap.put(openid,session);
        addOnlineCount();
        log.info("[{}]加入webSocket，当前连接人数为:{}，session:{}", openid, atomicInteger,session);
    }
    /**
     *  建立连接，关闭
     * @param openid
     */
    @OnClose
    public void onClose(@PathParam(value = "openid") String openid){
        concurrentHashMap.remove(openid);
        subOnlineCount();
        log.info("[{}]断开webSocket连接，当前人数为:{}", openid, atomicInteger);
    }

    /**
     * 收到客户的消息
     * @param message
     * @throws IOException
     */
    @OnMessage
    public void onMessage(String message)throws IOException{
        log.info("客户端[{}]已收到", message);
        message = "客户端：" + message + ",已收到";
        for (Session session: concurrentHashMap.values()) {
            try {
                sendMessage(session, message);
            } catch(Exception e){
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 错误时调用
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session,Throwable throwable){
        log.error("发生错误:{}", session);
        throwable.printStackTrace();
    }

    /**
     *  人数加1
     */
    public static void addOnlineCount(){
        atomicInteger.incrementAndGet();
    }

    /**
     * 人数减1
     */
    public static void subOnlineCount() {
        atomicInteger.decrementAndGet();
    }
}
