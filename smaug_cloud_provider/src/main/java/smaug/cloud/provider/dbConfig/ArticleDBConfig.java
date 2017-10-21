package smaug.cloud.provider.dbConfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
public class ArticleDBConfig extends DbConfigConst{

    /**
     * mapper 地址
     */
    public static final String MAPPER_PACKAGE = "smaug.cloud.provider.mapper.article";

    public static final String XML_PACKAGE = "classpath:mybatis/article/*.xml";


    @Bean(name = "articleDBSource")
    @Primary
    public DataSource articleDBSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(articleUrl);
        dataSource.setUsername(articleUsername);
        dataSource.setPassword(articlePassword);
        dataSource.setEnable(true);
        dataSource.setMaxActive(1000);
        return dataSource;
    }

    @Bean(name = "articleTransactionManager")
    @Primary
    public DataSourceTransactionManager articleTransactionManager() {
        return new DataSourceTransactionManager(articleDBSource());
    }

    @Bean(name = "articleSqlSessionFactory")
    @Primary
    public SqlSessionFactory articleSqlSessionFactory(@Qualifier("articleDBSource") DataSource articleDBSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(articleDBSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ArticleDBConfig.XML_PACKAGE));
        return sessionFactory.getObject();
    }
}
