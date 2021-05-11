package com.wer.importInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImportInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImportInfoApplication.class, args);
        System.out.println("~~~~~~~~exel导入启动成功！~~~~~~~~");
    }


}
