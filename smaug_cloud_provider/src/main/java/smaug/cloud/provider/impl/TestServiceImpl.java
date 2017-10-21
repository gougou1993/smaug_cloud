package smaug.cloud.provider.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import smaug.cloud.api.interfaces.TestService;
import smaug.cloud.api.vos.article.ArticleResponse;
import smaug.cloud.api.vos.queue.Queuing;
import smaug.cloud.api.vos.user.UserResponse;
import smaug.cloud.data.entity.article.ArticleEntity;
import smaug.cloud.data.entity.demo.UserEntity;
import smaug.cloud.data.entity.shop.ShopEntity;
import smaug.cloud.provider.mappers.shop.ShopEntityMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 17/10/10.
 */
@Service("testServiceImpl")
public class TestServiceImpl extends AbstractService implements TestService {

    @Value("${druid.type}")
    private String druidType;


    @Autowired
    private ShopEntityMapper shopEntityMapper;

    @Override
    public String test() {
        ShopEntity shopEntity = shopEntityMapper.selectByPrimaryKey(92809);
        return shopEntity.getShopname();
    }


    @Override
    public List<UserResponse> userList() {
        List<UserEntity> entities = new ArrayList<>();//userHelper.getUserList();
        List<UserResponse> responses = new ArrayList<>();
        entities.stream().map(e -> {
            UserResponse u = new UserResponse();
            u.setUserName(e.getName());
            u.setId(e.getId());
            u.setAge(e.getAge());
            return u;
        }).forEach(responses::add);
        return responses;
    }

    @Override
    public List<ArticleResponse> articleList() {
        List<ArticleEntity> articleEntities = new ArrayList<>();//articleHelper.getArticle(1);

        return new ArrayList<>();
    }

    @Override
    public List<Queuing> queue() {
        return queueHelper.queue();
    }
}
