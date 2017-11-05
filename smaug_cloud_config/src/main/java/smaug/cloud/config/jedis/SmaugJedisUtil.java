package smaug.cloud.config.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * Created by naonao on 17/11/5.
 */
@Component
public class SmaugJedisUtil implements JedisUtil {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void set(String key, String value, int expireSeconds) {
        set(key, value);
        jedisCluster.expire(key, expireSeconds);
    }
}
