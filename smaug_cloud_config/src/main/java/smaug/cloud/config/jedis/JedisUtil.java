package smaug.cloud.config.jedis;

import org.springframework.stereotype.Component;

/**
 * Created by naonao on 17/11/5.
 */
@Component
public interface JedisUtil {
    /**
     * set 值
     * @param key
     * @param value
     */
    void set(String key, String value);

    String get(String key);

    void set(String key, String value, int expireSeconds);

}
