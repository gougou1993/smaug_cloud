package smaug.cloud.common.utils.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
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

    private RetryTemplate retryTemplate;

    public SmaugJedisUtil(JedisCluster jedisCluster, RetryTemplate retryTemplate) {
        this.jedisCluster = jedisCluster;
        this.retryTemplate = retryTemplate;
    }

    @Override
    public void set(String key, String value) {
        retryTemplate.execute(
                context -> jedisCluster.set(key, value),
                context -> {
                    logger.info(": set =>  key:" + key + ", value:" + value
                            + ", retrying at " + context.getRetryCount() + " times", context.getLastThrowable());
                    return jedisCluster.set(key, value);
                }
        );
        jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    @Override
    public void set(String key, String value, int expireSeconds) {
        retryTemplate.execute(
                context -> jedisCluster.setex(key, expireSeconds, value),
                context -> {
                    logger.info(": set =>  key:" + key + ", value:" + value + ", expiredSeconds:" + expireSeconds
                            + ", retrying at " + context.getRetryCount() + " times", context.getLastThrowable());
                    return jedisCluster.setex(key, expireSeconds, value);
                }
        );
    }
}
