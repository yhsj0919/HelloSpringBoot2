package xyz.yhsj.spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisSuper {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 存储值
     * @param key
     * @param value
     */
    public void setRedisStringValue(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public String getRedisStringValue(String key){
        return stringRedisTemplate.opsForValue().get(key)==null?"":stringRedisTemplate.opsForValue().get(key);
    }

}