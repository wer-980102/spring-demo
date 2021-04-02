package com.wer.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    /**
     * Bean在实例化后会自动注册使用@ServerEndpoint 注解声名的 WebSocket endpoint
     * @return
     */
    @Bean
    public ServerEndpointExporter ServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
