package com.wer.redis.test;

import com.wer.redis.redismq.ListVer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestListVer {

    @Autowired
    private ListVer listVer;

    @Test
    void testGet(){
        List<String> result = listVer.getLpop("listmq");
        for(String message : result){
            System.out.println(message);
        }
    }

    @Test
    void testPut(){
        listVer.set("listmq","msgtest");
    }

}
