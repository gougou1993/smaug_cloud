package smaug.cloud.config.jedis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by naonao on 17/11/5.
 */
@ConfigurationProperties(prefix = "spring.redis.cluster")
@Getter
@Setter
@Component
public class JedisConfigProperties {
    private String nodes;

    private int maxIdle;

    private int minIdle;

    private int maxWaitMills;

    private boolean testOnBorrow;

    private boolean testOnReturn;

    private int connectionTimeOut;


}
