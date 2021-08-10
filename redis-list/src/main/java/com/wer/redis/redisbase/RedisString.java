package com.wer.redis.redisbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 操作字符串类型
 */
@Component
public class RedisString {

    public final static String RS_STR_NS = "rs:";

    @Autowired
    private JedisPool jedisPool;

    /**
     * 向Redis中存值，永久有效
     */
    public String set(String key,String message){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(RS_STR_NS+key,message);
        } catch (Exception e) {
            throw new RuntimeException("向Redis中存值失败！");
        } finally {
            jedis.close();
        }
    }

    /**
     * 批量向Redis中存值，永久有效
     */
    public String msetRaw(String... keysValues){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            /*自己去拆解，加前缀，再组合...*/
            return jedis.mset(keysValues);
        } catch (Exception e) {
            throw new RuntimeException("批量向Redis中存值失败！");
        } finally {
            jedis.close();
        }
    }

    /**
     * 根据传入Key获取指定Value
     */
    public String get(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(RS_STR_NS+key);
        } catch (Exception e) {
            throw new RuntimeException("获取Redis值失败！");
        }finally {
            jedis.close();
        }
    }
}
