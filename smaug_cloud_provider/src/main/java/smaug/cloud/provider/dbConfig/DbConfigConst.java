package smaug.cloud.provider.dbConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by naonao on 17/10/15.
 */
@Configuration
public class DbConfigConst {

    @Value("${druid.smaug.driver-class-name}")
    public String driverClassName = "com.mysql.cj.jdbc.Driver";

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
    public String articleUrl;


    @Value("${druid.smaug.article.username}")
    public String articleUsername;

    @Value("${druid.smaug.article.password}")
    public String articlePassword;

    @Value("${druid.smaug.queue.url}")
    public String queueUrl;

    @Value("${druid.smaug.queue.username}")
    public String queueUsername;

    @Value("${druid.smaug.queue.password}")
    public String queuePassword;


    @Value("${druid.smaug.shop.url}")
    public String shopUrl;

    @Value("${druid.smaug.shop.username}")
    public String shopUsername;

    @Value("${druid.smaug.shop.password}")
    public String shopPassword;


}
