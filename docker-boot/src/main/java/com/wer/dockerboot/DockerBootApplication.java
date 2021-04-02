package com.wer.dockerboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerBootApplication {

    public static void main(String[] args) {

        SpringApplication.run(DockerBootApplication.class, args);
        System.out.println("~~~~~~demo启动成功！~~~~~");
    }

}
