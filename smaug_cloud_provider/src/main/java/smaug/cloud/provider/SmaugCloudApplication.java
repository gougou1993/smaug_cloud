package smaug.cloud.provider;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.HttpMessageConverter;
import smaug.cloud.config.jerseryConfig.AnnotationJerseyConfig;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by Allen on 17/10/10.
 */

@SpringBootApplication
@ComponentScan(value = {"smaug.cloud.config", "smaug.cloud.provider", "smaug.cloud.common", "smaug.cloud.consumer"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = "smaug.cloud.provider.mappers")
public class SmaugCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmaugCloudApplication.class, args);
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
    public Queue queue(){
        return new ActiveMQQueue("mytest.queue");
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic("mytest.topic");
    }

}
