package smaug.cloud.provider.impl;


import org.springframework.stereotype.Service;
import smaug.cloud.api.interfaces.TestService;
import smaug.cloud.api.vos.article.ArticleResponse;
import smaug.cloud.api.vos.queue.Queuing;
import smaug.cloud.api.vos.user.UserResponse;
import smaug.cloud.data.entity.article.ArticleEntity;
import smaug.cloud.data.entity.demo.UserEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Allen on 17/10/10.
 */
@Service("testServiceImpl")
public class TestServiceImpl extends AbstractService implements TestService {


    @Override
    public String test() {
        commonMessageUtil.convertAndSend("胖云是笨蛋");
        mailUtil.sendTextMail("test", "胖云是笨蛋");
        return "succ";
    }

    @Override
    public String test2() {
        return "";
    }

    @Override
    public List<UserResponse> userList() {
        List<UserEntity> entities = new ArrayList<>();
        List<UserResponse> responses = new ArrayList<>();
        return responses;
    }

    @Override
    public List<ArticleResponse> articleList() {
        List<ArticleEntity> articleEntities = new ArrayList<>();
        return new ArrayList<>();
    }

    @Override
    public List<Queuing> queue() {
        return Collections.emptyList();
    }
}
