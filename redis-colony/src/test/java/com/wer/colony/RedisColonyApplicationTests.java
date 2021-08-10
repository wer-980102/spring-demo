package com.wer.colony;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wer.colony.dao.ShopUserMapper;
import com.wer.colony.model.ShopUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
class RedisColonyApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ShopUserMapper shopUserMapper;


    @Test
    void contextLoads() {
    }

    /**
     * String 类型
     */
    @Test
    public void test1(){
        /** 字符串 **/
      /*  redisTemplate.opsForValue().set("name1","zhangailing");
        System.out.println(redisTemplate.opsForValue().get("name1"));*/

        /** 类 **/
       /* ShopUser shopUser = shopUserMapper.selectByPrimaryKey(1L);
        //设置k,v
        redisTemplate.opsForValue().set("name", shopUser);
        Object user = redisTemplate.opsForValue().get("name");
        //    JSONObject
        String userName = JSON.toJSONString(user);
        ShopUser shopUser1 = JSONObject.parseObject(userName, ShopUser.class);
        //取值
        System.out.println(shopUser1.getUserName());*/

        /** 集合 **/
       /* List<ShopUser> userList = shopUserMapper.getUserList();*/
        //批量取值
        List<String> keys = new ArrayList<String>();
        keys.add("k1");
        keys.add("k2");
        keys.add("k3");
        redisTemplate.opsForValue().set("list",keys.toString());

        Object name = redisTemplate.opsForValue().get("list");
        String s = JSON.toJSONString(name);

        String shopUser = JSONObject.parseObject(s, String.class);

        List list = Arrays.asList(shopUser.split(","));

        List newlist = new ArrayList(list);
        newlist.stream().forEach(su->{
            System.out.println("Name:"+su);
        });
    }
}
