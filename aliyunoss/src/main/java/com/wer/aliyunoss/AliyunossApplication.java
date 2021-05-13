package com.wer.aliyunoss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AliyunossApplication {

    public static void main(String[] args) {
        System.out.println("~~~~~~阿里云OSS服务启动成功！~~~~~~");
        SpringApplication.run(AliyunossApplication.class, args);
    }

}
