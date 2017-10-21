package smaug.cloud.provider.dbConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by naonao on 17/7/16.
 */
@Configuration
@MapperScan(basePackages = OrderDBConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = "queuesSqlSessionFactory")
public class OrderDBConfig extends DbConfigConst {
    /**
     * mybatis 实体类
     */
    public static final String MAPPER_PACKAGE = "smaug.cloud.provider.mappers.queue";

    /**
     * sql xml
     */
    public static final String MAPPER_LOCATION = "classpath:mybatis/queue/*.xml";


    @Bean(name = "queuesDBSource")
    public DataSource queuesDBSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(queueUrl);
        dataSource.setUsername(queueUsername);
        dataSource.setPassword(queuePassword);
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
