package com.wer.parking;

import com.wer.parking.ws.netty.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class ParkingTestApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ParkingTestApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ‘停车场测试模块启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }

    @Autowired
    NettyServer server;

    @Override
    public void run(String... args) throws Exception {
        CompletableFuture.runAsync(() -> server.start(new InetSocketAddress(20773)));
    }
}
