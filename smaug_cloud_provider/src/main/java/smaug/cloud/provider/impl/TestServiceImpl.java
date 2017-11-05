package smaug.cloud.provider.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import smaug.cloud.api.interfaces.TestService;
import smaug.cloud.api.vos.article.ArticleResponse;
import smaug.cloud.api.vos.queue.Queuing;
import smaug.cloud.api.vos.user.UserResponse;
import smaug.cloud.config.jedis.JedisConfigProperties;
import smaug.cloud.config.jedis.JedisUtil;
import smaug.cloud.config.jedis.SmaugJedisUtil;
import smaug.cloud.data.entity.article.ArticleEntity;
import smaug.cloud.data.entity.demo.UserEntity;
import smaug.cloud.provider.mappers.article.ArticleEntityMapper;
import smaug.cloud.provider.mappers.demo.UserEntityMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Allen on 17/10/10.
 */
@Service("testServiceImpl")
public class TestServiceImpl extends AbstractService implements TestService {

    @Value("${druid.type}")
    private String druidType;


    @Autowired
    private ArticleEntityMapper articleEntityMapper;

    @Autowired
    private UserEntityMapper userEntityMapper;


    @Override
    public String test() {
        for (int i = 0; i < 100; i++) {
            String key = "naonao" + i;
            smaugJedisUtil.set(key, key, 300);
        }
        logger.info("redis");
        return smaugJedisUtil.get("naonao1");
    }

    @Override
    public String test2() {
        return "";
    }

    @Override
    public List<UserResponse> userList() {
        List<UserEntity> entities = new ArrayList<>();//userHelper.getUserList();
        List<UserResponse> responses = new ArrayList<>();
        return responses;
    }

    @Override
    public List<ArticleResponse> articleList() {
        List<ArticleEntity> articleEntities = new ArrayList<>();//articleHelper.getArticle(1);

        return new ArrayList<>();
    }

    @Override
    public List<Queuing> queue() {
        return Collections.emptyList();
    }
}
