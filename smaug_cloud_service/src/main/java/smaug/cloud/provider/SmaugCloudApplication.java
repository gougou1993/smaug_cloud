package smaug.cloud.provider;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import smaug.cloud.config.jerseryConfig.AnnotationJerseyConfig;

/**
 * Created by Allen on 17/10/10.
 */

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
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

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        FastJsonConfig jsonConfig = new FastJsonConfig();
        jsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        converter.setFastJsonConfig(jsonConfig);

        HttpMessageConverter<?> httpConverter = converter;
        return new HttpMessageConverters(httpConverter);
    }

}
