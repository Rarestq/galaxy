package com.wuxiu.galaxy.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wuxiu
 */
@Component
public class RedisCacheTemplate {

    private static Logger logger = LoggerFactory.getLogger(RedisCacheTemplate.class);

    @Autowired
    private JedisPool jedisPool;

    private Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        if (jedis == null) {
            logger.error("Redis Connect Error");
            throw new RuntimeException("Redis 连接有问题啦！");
        }
        return jedis;
    }

    /**
     * 获取字符串
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.get(key);
        } finally {
            close(jedis);
        }
    }

    /**
     * 设置失效时间
     *
     * @param key
     * @param value
     * @param second (单位：秒)
     */
    public boolean setString(String key, String value, int second) {
        Jedis jedis = getJedis();
        try {
            String result = jedis.setex(key, second, value);

            return (Objects.nonNull(result) && "OK".equals(result));
        } finally {
            close(jedis);
        }
    }

    /**
     * 缓存，无时效性
     *
     * @param key
     * @param value
     */
    public boolean setString(String key, String value) {
        Jedis jedis = getJedis();
        try {
            String result = jedis.set(key, value);

            return (Objects.nonNull(result) && "OK".equals(result));
        } finally {
            close(jedis);
        }
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param second
     * @return
     */
    public boolean expire(String key, int second) {
        Jedis jedis = getJedis();
        try {
            Long result = jedis.expire(key, second);

            return (result != null && result == 1L);
        } finally {
            close(jedis);
        }
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void del(String key) {
        try (Jedis jedis = getJedis()) {
            jedis.del(key);
        }
    }

    private void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public List<String> getList(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        if (StringUtils.isEmpty(value)) {
            return new ArrayList<>();
        }

        List<String> list = JSON.parseArray(value, String.class);
        jedis.close();
        return list;
    }
}
