package smaug.cloud.provider.dbConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by naonao on 17/10/15.
 */
@Configuration
public class DbConfigConst {

    @Value("${druid.smaug.driver-class-name}")
    public String driverClassName;

    @Value("${druid.smaug.initial-size}")
    public int initialSize;

    @Value("${druid.smaug.min-idle}")
    public int minIdle;

    @Value("${druid.smaug.max-active}")
    public int maxActive;

    @Value("${druid.smaug.test-on-borrow}")
    public boolean testOnBorrow;


    /**
     * demo 数据库
     */
    @Value("${druid.smaug.demo.url}")
    public String demoUrl;

    @Value("${druid.smaug.demo.username}")
    public String demoUsername;


    @Value("${druid.smaug.demo.password}")
    public String demoPassword;

    /**
     *
     */
    @Value("${druid.smaug.article.url}")
    public static String activeUrl;


    @Value("${druid.smaug.article.username}")
    public static String activeUsername;

    @Value("${druid.smaug.article.password}")
    public static String activePassword;


}
