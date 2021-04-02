package com.wer.springwork;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
class SpringWorkApplicationTests {

    /**
     * 类路径获取配置文件
     */
    @Test
    void contextLoads() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    }

}
