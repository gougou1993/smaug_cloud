package smaug.cloud.provider;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.I0Itec.zkclient.ZkClient;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import smaug.cloud.common.utils.mail.MailUtil;
import smaug.cloud.common.utils.mq.SmaugMessageUtil;
import smaug.cloud.config.jerseryConfig.AnnotationJerseyConfig;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Allen on 17/10/10.
 */

@EnableEurekaServer
@SpringBootApplication
@ComponentScan(value = {"smaug.cloud.config", "smaug.cloud.provider", "smaug.cloud.common"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "smaug.cloud.provider.mappers")
public class SmaugCloudApplication {

    @Value("${spring.activemq.broker-url}")
    private String commonBrokerUrl;

    @Value("${spring.activemq.pool.max-connections}")
    private int mqMaxConnections;

    @Value("${spring.activemq.receiveTimeOut}")
    private long receiveTimeOut;

    @Value(("${spring.activemq.autoBackUp}"))
    private boolean autoBackUp;

    @Value("${spring.activemq.smaugCommonQueue}")
    private String smaugCommonQueue;

    @Value("${spring.mail.host}")
    private String smtpHost;

    @Value("${spring.mail.port}")
    private String smtpPort;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String userPassword;

    @Value("${spring.mail.nickname}")
    private String nickname;

    @Value("${spring.mail.receiver}")
    private String receiver;

    @Value("${zk-host}")
    private String zkHost;

    public static void main(String[] args) {
        //SpringApplication.run(SmaugCloudApplication.class, args);
        new SpringApplicationBuilder(SmaugCloudApplication.class).web(true).run(args);
    }

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, AnnotationJerseyConfig.class.getName());
        return registration;
    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converterList) {
//        super.configureMessageConverters(converterList);
//        //定义一个converter
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig config = new FastJsonConfig();
//        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        List<MediaType> types = new ArrayList<>();
//        types.add(MediaType.APPLICATION_JSON_UTF8);
//
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(types);
//        fastJsonHttpMessageConverter.setFastJsonConfig(config);
//        converterList.add(fastJsonHttpMessageConverter);
//    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        converter.setFastJsonConfig(jsonConfig);

        HttpMessageConverter<?> httpConverter = converter;
        return new HttpMessageConverters(httpConverter);
    }

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("mytest.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("mytest.topic");
    }


    /**
     * mq 连接池配置
     *
     * @return
     */
    @Bean
    public PooledConnectionFactory commonConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(commonBrokerUrl);
        activeMQConnectionFactory.setMaxThreadPoolSize(mqMaxConnections);

        PooledConnectionFactory factory = new PooledConnectionFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setCreateConnectionOnStartup(false);
        return factory;
    }

    @Bean(name = "commonMessageUtil")
    public SmaugMessageUtil commonMessageUtil() {
        SmaugMessageUtil smaugMessageUtil = new SmaugMessageUtil(commonConnectionFactory(), receiveTimeOut, Arrays.asList(smaugCommonQueue));
        smaugMessageUtil.setAutoBackUp(autoBackUp);
        return smaugMessageUtil;
    }

    @Bean(name = "mailUtil")
    public MailUtil mailUtil() {
        Properties properties = new Properties();
        properties.setProperty("smtpHost", smtpHost);
        properties.setProperty("smtpPort", smtpPort);
        properties.setProperty("userName", username);
        properties.setProperty("userPassword", userPassword);
        properties.setProperty("nickName", nickname);
        properties.setProperty("receiver", receiver);

        return new MailUtil(properties, true);
    }

    @Bean(name = "zkClient")
    public ZkClient zkClient() {
        return new ZkClient(zkHost);
    }
}
