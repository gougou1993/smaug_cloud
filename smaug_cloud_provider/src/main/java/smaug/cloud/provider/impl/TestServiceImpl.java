package smaug.cloud.provider.impl;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smaug.cloud.api.interfaces.TestService;
import smaug.cloud.api.vos.article.ArticleResponse;
import smaug.cloud.api.vos.queue.Queuing;
import smaug.cloud.api.vos.user.UserResponse;
import smaug.cloud.common.consts.MQConst;
import smaug.cloud.common.utils.mq.ActiveMQProducer;
import smaug.cloud.data.entity.article.ArticleEntity;
import smaug.cloud.data.entity.demo.UserEntity;
import javax.jms.Destination;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Allen on 17/10/10.
 */
@Service("testServiceImpl")
public class TestServiceImpl extends AbstractService implements TestService {

    @Autowired
    private ActiveMQProducer activeMQProducer;

    @Override
    public String test() {
        commonMessageUtil.convertAndSend("wowoxiangdemandaren");
        return mqConst.getSmaugCommonQueue();
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
