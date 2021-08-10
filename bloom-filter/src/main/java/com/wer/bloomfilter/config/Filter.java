package com.wer.bloomfilter.config;

import com.wer.bloomfilter.dao.ShopUserMapper;
import com.wer.bloomfilter.model.ShopUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Filter {

    private static final Logger logger = LoggerFactory.getLogger(Filter.class);

    @Autowired
    private ShopUserMapper shopUserMapper;

    @PostConstruct
    public void init(){
        List<ShopUser> userList = shopUserMapper.getUserList();
        CountingBloomFilter.size =31;
        userList.stream().forEach(info->{
            CountingBloomFilter.add(info.getUserName());
            logger.info("插入布隆：[" + info.getUserName() + "]");
        });
        logger.info("------初始化成功！------");

    }

}
