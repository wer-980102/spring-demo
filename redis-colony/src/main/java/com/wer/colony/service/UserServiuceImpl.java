package com.wer.colony.service;

import com.wer.colony.dao.ShopUserMapper;
import com.wer.colony.model.ShopUser;
import com.wer.colony.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiuceImpl {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ShopUserMapper shopUserMapper;


    public ShopUser redisStringType(Long userId){

        ShopUser user = null;
        String key = Common.REDIS_TYPE + userId;
         user = (ShopUser)redisTemplate.opsForValue().get(key);
        if(null != user){
            System.out.println("从redis缓存中拿.....");
            return user;
        }
        synchronized (shopUserMapper){
             user = (ShopUser)redisTemplate.opsForValue().get(key);
            if(null != user){
                System.out.println("从redis缓存中拿.....");
                return user;
            }
            user = shopUserMapper.selectByPrimaryKey(userId);
            if(null != user){
                redisTemplate.opsForValue().set(key,userId,2, TimeUnit.DAYS);
            }else{
                //为了防止缓存穿透，缓存时间一定要短，空数据没必要占用缓存内存
                redisTemplate.opsForValue().set(key,userId,5, TimeUnit.MINUTES);
            }
        }
        return user;
    }
}
