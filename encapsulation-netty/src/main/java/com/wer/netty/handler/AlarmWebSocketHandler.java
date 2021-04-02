package com.wer.netty.handler;

import com.wer.netty.constation.NettyChannel;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * description : webSocket业务处理
 */
@RestController
@RequestMapping("/api/sysFeign")
//@CrossOrigin(allowCredentials = "true")
public class AlarmWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static AlarmWebSocketHandler alarmWebSocketHandler;


    @PostConstruct
    public void init() {
        alarmWebSocketHandler = this;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private static String CHANNELMAP = "channelMap";


    /** 20775*/
    //存储ip和channel的容器
    private static ConcurrentMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    private static ConcurrentMap<String, ConcurrentMap> control20111 = new ConcurrentHashMap<>();

    /** 总通道*/
    private static ConcurrentMap<String, ConcurrentMap<String, ConcurrentMap>> control = new ConcurrentHashMap<>();

    static {
        /** 二级控制*/
        //20775
        control20111.put(CHANNELMAP, channelMap);

        /** 一级控制*/
        control.put(NettyChannel.CHANNEL_THREE, control20111);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        String channelNumber = incoming.localAddress().toString().substring(incoming.localAddress().toString().lastIndexOf(":") + 1);
        logger.info("通道号为" + channelNumber);
        logger.info("与客户端建立连接，通道开启！" + incoming.remoteAddress().toString());
        //添加到channelGroup通道组
        AlarmChannelHandlerPool.channelGroup.add(ctx.channel());
        control.get(channelNumber).get(CHANNELMAP).put(incoming.remoteAddress().toString(), ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        String channelNumber = incoming.localAddress().toString().substring(incoming.localAddress().toString().lastIndexOf(":") + 1);
        logger.info("与客户端断开连接，通道关闭！");
        //从channelGroup通道组移除
        AlarmChannelHandlerPool.channelGroup.remove(ctx.channel());
        control.get(channelNumber).get(CHANNELMAP).remove(incoming.remoteAddress().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel incoming = ctx.channel();
        String channelNumber = incoming.localAddress().toString().substring(incoming.localAddress().toString().lastIndexOf(":") + 1);
        //首次连接是FullHttpRequest，处理参数
        if (null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            Map<String, String> paramMap = new HashMap();
            //获取请求中的参数
            if (request.method() == HttpMethod.GET) {
                getParams(paramMap, request);
            } else if (request.method() == HttpMethod.POST) {
                postParams(paramMap, request);
            } else {
                paramMap = getUrlParams(uri);
            }

            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                logger.info(newUri);
                request.setUri(newUri);
            }
        } else if (msg instanceof TextWebSocketFrame) {
            //text消息类型
            TextWebSocketFrame frame = (TextWebSocketFrame) msg;
            logger.info("通道收到服务器数据：" + frame.text() + "   对应连接IP和端口为：" + incoming.remoteAddress().toString());
            //给指定连接通道分配对应的通道key  a+frame.text()
            //sendMessage(incoming.remoteAddress().toString());
        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }

    /**
     * 群发给所有channel
     * @param message
     * @param channelNumber
     */
    @GetMapping(value = "/sendAllMessage")
    public void sendAllMessage(String message, String channelNumber) {
        //收到信息后，群发给所有channel
        AlarmChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

    /**
     * 给指定用户发内容(key)
     * 后续可以掉这个方法推送消息给客户端
     */
    public void sendMessage(String address) {
        Channel channel = channelMap.get(address);
        channel.writeAndFlush(new TextWebSocketFrame(address));
    }


    /**
     * 根据租户推送消息标准格式
     * data 消息内容:json {}里内串
     * state 消息类型
     * tenantId 租户id
     * channelNumber 通道号
     */
    @GetMapping(value = "/sendMessageByTenantId")
    public void sendMessageByTenantId(String data,String state,String channelNumber) throws Exception {
        String[] channelNumberArray = channelNumber.split(",");
        for(String channelNumbers:channelNumberArray){
            sendMessageByTenantIdImpl(data,state,channelNumbers);
        }
    }

    /**
     * 推送给相应通道
     * @param data
     * @param state
     * @param channelNumber
     */
    private void sendMessageByTenantIdImpl(String data,String state, String channelNumber){
   /*     Channel channel = (Channel) control.get(channelNumber).get(CHANNELMAP);
        if (channel == null) {
            return;
        }*/

        ChannelGroupFuture channelFutures = AlarmChannelHandlerPool.channelGroup.writeAndFlush(new TextWebSocketFrame("{\"state\":\"" + state + "\",\"data\":{" + data + "}}"));
        channelFutures.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                //写操作完成，并没有错误发生
                if (future.isSuccess()) {
                    //logger.info("消息发送成功");
                } else {
                    //记录错误
                    logger.info("通知公告消息发送失败");
                    future.cause().printStackTrace();
                }
            }
        });
    }


    /**
     * 地址解析
     * @param url
     * @return
     */
    private static Map getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }

    /**
     * 解析get请求参数
     */

    private void getParams(Map<String, String> parmMap, FullHttpRequest fullReq) {
        // 是GET请求
        QueryStringDecoder decoder = new QueryStringDecoder(fullReq.uri());
        decoder.parameters().entrySet().forEach(entry -> {
            // entry.getValue()是一个List, 只取第一个元素
            parmMap.put(entry.getKey(), entry.getValue().get(0));
        });
    }

    /**
     * 解析post 请求参数
     */
    private void postParams(Map<String, String> parmMap, FullHttpRequest fullReq) throws IOException {
        // 是POST请求
        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullReq);
        decoder.offer(fullReq);

        List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();

        for (InterfaceHttpData parm : parmList) {

            Attribute data = (Attribute) parm;
            try {
                parmMap.put(data.getName(), data.getValue());
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

}
