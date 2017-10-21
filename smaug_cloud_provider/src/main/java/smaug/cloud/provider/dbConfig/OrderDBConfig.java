package smaug.cloud.provider.dbConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import smaug.service.config.dbConfig.DBConfig;

import javax.sql.DataSource;

/**
 * Created by naonao on 17/7/16.
 */
@Configuration
@MapperScan(basePackages = OrderDBConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = "")
public class OrderDBConfig {
    /**
     * mybatis 实体类
     */
    public static final String MAPPER_PACKAGE = "smaug.service.provider.mapper.orders";

    /**
     * sql xml
     */
    public static final String MAPPER_LOCATION = "classpath:mybatis/orders/*.xml";

    @Autowired
    private DBConfig dbConfig;

    @Bean(name = "queuesDBSource")
    public DataSource queuesDBSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dbConfig.getDriverClassName());
        dataSource.setUrl(dbConfig.getQueuesUrl());
        dataSource.setUsername(dbConfig.getQueuesUserName());
        dataSource.setPassword(dbConfig.getQueuesPassword());
        dataSource.setEnable(true);
        dataSource.setMaxActive(1000);
        return dataSource;
    }

    @Bean(name = "queuesTransactionManager")
    public DataSourceTransactionManager queuesTransactionManager() {
        return new DataSourceTransactionManager(queuesDBSource());
    }

    @Bean(name = "queuesSqlSessionFactory")
    public SqlSessionFactory queuesSqlSessionFactory(@Qualifier("queuesDBSource") DataSource queuesDBSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(queuesDBSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OrderDBConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
