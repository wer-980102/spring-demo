package com.wer.netty.controller;

import com.wer.netty.websocket.NioWebSocketHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {
        @Resource
        private NioWebSocketHandler nioWebSocketHandler;

        @GetMapping("/netty")
        public void getInfo(){
            log.info("我进来了！");
            nioWebSocketHandler.setHandlerWebSocketFrame(new TextWebSocketFrame("我是测试数据！"));
        }
}
