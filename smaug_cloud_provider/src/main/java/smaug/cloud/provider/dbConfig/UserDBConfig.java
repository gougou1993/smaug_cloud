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
@MapperScan(basePackages = UserDBConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = "demoSqlSessionFactory")
public class UserDBConfig extends DbConfigConst {
    /**
     * mybatis 实体类
     */
    public static final String MAPPER_PACKAGE = "smaug.cloud.provider.mappers.demo";

    /**
     * sql xml
     */
    public static final String MAPPER_LOCATION = "classpath:mybatis/demo/*.xml";


    @Bean(name = "demoDBSource")
    public DataSource demoDBSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(demoUrl);
        dataSource.setUsername(demoUsername);
        dataSource.setPassword(demoPassword);
        dataSource.setEnable(true);
        dataSource.setMaxActive(1000);
        return dataSource;
    }

    @Bean(name = "demoTransactionManager")
    public DataSourceTransactionManager demoTransactionManager() {
        return new DataSourceTransactionManager(demoDBSource());
    }

    @Bean(name = "demoSqlSessionFactory")
    public SqlSessionFactory demoSqlSessionFactory(@Qualifier("demoDBSource") DataSource demoDBSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(demoDBSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(UserDBConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
