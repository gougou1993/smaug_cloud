package smaug.cloud.config.jedis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by naonao on 17/11/5.
 */
@Configuration
@ConditionalOnClass(JedisCluster.class)
@EnableConfigurationProperties(JedisConfigProperties.class)
public class JedisClusterConfig {

    @Resource
    private JedisConfigProperties jedisConfigProperties;

    @Bean
    public JedisCluster jedisClusterFactory() {
        String[] serverArray = jedisConfigProperties.getNodes().split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, jedisConfigProperties.getConnectionTimeOut());
    }

    @Bean
    public RedisTemplate redisTemplateFactory() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * redisCluster配置
     *
     * @return
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("spring.redis.cluster.nodes", jedisConfigProperties.getNodes());
        source.put("spring.redis.cluster.timeout", jedisConfigProperties.getConnectionTimeOut());
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisClusterConfiguration(), jedisPoolConfig());
    }

    /**
     * 生成 RetryTemplate 用于代替xml 里的<bean></bean> 属性
     * @return
     */
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
        policy.setTimeout(50);
        retryTemplate.setRetryPolicy(policy);
        return retryTemplate;
    }

    /**
     * jedis 连接池
     * @return
     */
    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisConfigProperties.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(jedisConfigProperties.getMaxWaitMills());
        jedisPoolConfig.setMinIdle(jedisConfigProperties.getMinIdle());
        jedisPoolConfig.setTestOnBorrow(jedisConfigProperties.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(jedisConfigProperties.isTestOnReturn());

        return jedisPoolConfig;
    }
}

