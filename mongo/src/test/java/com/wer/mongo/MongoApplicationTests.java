package com.wer.mongo;

import com.wer.mongo.model.AyUserAttachmentRel;
import com.wer.mongo.service.AyUserAttachmentRelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class MongoApplicationTests {

    @Resource
    private AyUserAttachmentRelService ayUserAttachmentRelService;

    @Test
    void contextLoads() {
    }


    @Test
    void testMongo() {
        ayUserAttachmentRelService.save(AyUserAttachmentRel.builder().id("1").userId("001").fileName("wer").build());
        log.info("±£´æ³É¹¦£¡");
    }
}
