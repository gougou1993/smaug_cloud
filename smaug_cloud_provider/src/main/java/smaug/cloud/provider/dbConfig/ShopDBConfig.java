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

import javax.sql.DataSource;

/**
 * Created by naonao on 17/8/5.
 */
@Configuration
public class ShopDBConfig extends DbConfigConst{

    /**
     * mapper 地址
     */
    public static final String MAPPER_PACKAGE = "smaug.cloud.provider.mapper.shop";

    public static final String XML_PACKAGE = "classpath:mybatis/shop/*.xml";


    @Bean(name = "shopsDBSource")
    @Primary
    public DataSource shopsDBSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(shopUrl);
        dataSource.setUsername(shopUsername);
        dataSource.setPassword(shopPassword);
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
