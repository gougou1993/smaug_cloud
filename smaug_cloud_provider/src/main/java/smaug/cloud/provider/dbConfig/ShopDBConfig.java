package smaug.cloud.provider.dbConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import smaug.service.config.dbConfig.DBConfig;

import javax.sql.DataSource;

/**
 * Created by naonao on 17/8/5.
 */
@Configuration
public class ShopDBConfig {

    /**
     * mapper 地址
     */
    public static final String MAPPER_PACKAGE = "smaug.service.provider.mapper.shops";

    public static final String XML_PACKAGE = "classpath:mybatis/shops/*.xml";

    @Autowired
    private DBConfig dbConfig;

    @Bean(name = "shopsDBSource")
    @Primary
    public DataSource shopsDBSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriverClassName());
        dataSource.setUrl(dbConfig.getShopUrl());
        dataSource.setUsername(dbConfig.getShopUserName());
        dataSource.setPassword(dbConfig.getShopPassword());
        dataSource.setEnable(true);
        dataSource.setMaxActive(1000);
        return dataSource;
    }

    @Bean(name = "shopsTransactionManager")
    @Primary
    public DataSourceTransactionManager shopsTransactionManager() {
        return new DataSourceTransactionManager(shopsDBSource());
    }

    @Bean(name = "shopsSqlSessionFactory")
    @Primary
    public SqlSessionFactory shopsSqlSessionFactory(@Qualifier("shopsDBSource") DataSource shopsDBSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(shopsDBSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ShopDBConfig.XML_PACKAGE));
        return sessionFactory.getObject();
    }
}
