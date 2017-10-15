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
 * Created by naonao on 17/10/15.
 */
@Configuration
@MapperScan(basePackages = ArticleDbConfig.MAPPER_PACKAGE, sqlSessionFactoryRef = "")
public class ArticleDbConfig extends DbConfigConst {
    /**
     * 实体类
     */
    public static final String MAPPER_PACKAGE = "smaug.cloud.provider.mappers.article";

    /**
     * xml 配置
     */
    public static final String MAPPER_LOCATION = "classpath:mybatis/article/*.xml";


    @Bean(name = "articleDBSource")
    public DataSource articleDBSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(articleUrl);
        dataSource.setUsername(articleUsername);
        dataSource.setPassword(articlePassword);
        dataSource.setEnable(true);
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        return dataSource;
    }

    @Bean(name = "demoTransactionManager")
    public DataSourceTransactionManager demoTransactionManager() {
        return new DataSourceTransactionManager(articleDBSource());
    }

    @Bean(name = "demoSqlSessionFactory")
    public SqlSessionFactory demoSqlSessionFactory(@Qualifier("articleDBSource") DataSource demoDBSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(demoDBSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(ArticleDbConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}