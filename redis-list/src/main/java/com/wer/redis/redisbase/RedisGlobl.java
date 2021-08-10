package com.wer.redis.redisbase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 全局操作命令
 */
@Component
public class RedisGlobl {

    @Autowired
    private JedisPool jedisPool;

    /**
     * keys  Set 插入
     * @param params
     * @return
     */
    public Set<String> keys(String params){
        Jedis jedis = null;
        Set<String> keys;

        try {
            jedis = jedisPool.getResource();
            keys = jedis.keys(params);
        } catch (Exception e) {
            return  null;
        } finally {
            jedis.close();
        }
        return keys;
    }

    /**
     * 查询有多少个值
     * @return
     */
    public Long dbsize() {
        Jedis jedis = null;
        Long dbSize;
        try {
            jedis = jedisPool.getResource();
            dbSize = jedis.dbSize();
        } catch (Exception e) {
            return null;
        } finally {
            jedis.close();
        }
        return dbSize;
    }

    /**
     * 查询还key是否存在
     * @param key
     * @return
     */
    public Boolean existskey(String key) {
        Jedis jedis = null;
        Boolean isExists;
        try {
            jedis = jedisPool.getResource();
            isExists = jedis.exists(key);
        } catch (Exception e) {
            return false;
        } finally {
            jedis.close();
        }
        return isExists;
    }

    /**
     * 查询生存到期时间
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(String key,long seconds) {
        Jedis jedis = null;
        Long result;
        try {
            jedis = jedisPool.getResource();
            result = jedis.expire(key,seconds);
        } catch (Exception e) {
            return null;
        } finally {
            jedis.close();
        }
        return result;
    }

    /**
     *  获取key的剩余时间
     * @param key
     * @return
     */
    public Long ttl(String key) {
        Jedis jedis = null;
        Long duration;
        try {
            jedis = jedisPool.getResource();
            duration = jedis.ttl(key);
        } catch (Exception e) {
            return null;
        } finally {
            jedis.close();
        }
        return duration;
    }

}
