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
    public RedisTemplate redisTemplateFactory(){
        RedisTemplate redisTemplate =new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        //指定具体序列化方式  不过这种方式不是很好,一个系统中可能对应值的类型不一样,如果全部使用StringRedisSerializer 序列化
        //会照成其他类型报错,所以还是推荐使用第一种,直接指定泛型的类型,spring 会根据指定类型序列化。
        //redisTemplate.setKeySerializer( new StringRedisSerializer());
        //redisTemplate.setValueSerializer(new StringRedisSerializer());
        //redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * redisCluster配置
     * @return
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("spring.redis.cluster.nodes", jedisConfigProperties.getNodes());
        source.put("spring.redis.cluster.timeout", jedisConfigProperties.getConnectionTimeOut());
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }


    /**
     * 其实在JedisConnectionFactory的afterPropertiesSet()方法 中
     * if(cluster !=null) this.cluster =createCluster();
     * 也就是当
     * spring.redis.cluster.nodes 配置好的情况下,就可以实例化 JedisCluster.
     * 也就是说,我们使用JedisCluster 的方式只需要在application.properties 配置文件中
     *
     * #redis cluster
     *  spring.redis.cluster.nodes=127.0.0.1:7000,127.0.0.1:7001,127.0.0.1:7002
     *
     * RedisTemplate.afterPropertiesSet() 中查看到最终方法中使用了JedisCluster 对象。
     * 也就是说 redisTemplate依赖jedis ,内部操作的就是jedis,同理内部也操作jedisCluster.
     *
     *
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

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(redisClusterConfiguration(), jedisPoolConfig());
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();
        TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
        policy.setTimeout(50);
        retryTemplate.setRetryPolicy(policy);
        return retryTemplate;
    }
}

