package app.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis 工具类
 *
 * @Author: bai
 * @Date: 2021/11/19 14:45
 */

@Component
public class RedisUtils {

    private StringRedisTemplate stringRedisTemplate;

    public RedisUtils(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private static final Long DEFAULT_EXPIRE_TIME = -1L;


    /**
     * 存储 k-v
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE_TIME);
    }

    /**
     * 将值存入 redis
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public void set(String key, Object value, Long expireTime) {
        if (value == null) {
            return;
        }
        String jsonValue = JsonUtils.obj2json(value);
        stringRedisTemplate.opsForValue().set(key, jsonValue, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 判断 key 是否存在
     *
     * @param key
     * @return
     */
    public boolean existKey(String key) {
        Boolean has = stringRedisTemplate.hasKey(key);
        return has != null && has;
    }

    /**
     * 查询 value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 查询 value 并指定序列化对象
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value == null) {
            return null;
        }
        return JsonUtils.json2obj(value, clazz);
    }


    /**
     * 删除一条数据
     *
     * @param key
     */
    public boolean delete(String key) {
        Boolean isDelete = stringRedisTemplate.delete(key);
        return isDelete != null && isDelete;
    }
}
















































