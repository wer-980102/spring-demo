package com.wer.netty;

import com.wer.netty.nettyMessage.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

@EnableAsync
@EnableScheduling
@SpringBootApplication
public class EncapsulationNettyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EncapsulationNettyApplication.class, args);
        System.out.println("~~~~~WebSocket 服务启动成功！~~~~~");
    }

    @Autowired
    NettyServer server;

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture.runAsync(() -> server.start(new InetSocketAddress(20772)));
    }

}
