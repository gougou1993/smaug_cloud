package smaug.cloud.common.utils.mq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.Queue;

/**
 * author Allen
 *
 * @since 2017-11-06 下午1:58
 */
@Service("activeMQProducer")
public class ActiveMQProducer {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Autowired
//    private Queue queue;

    @Resource(name = "commonMessageUtil")
    private SmaugMessageUtil commonMessageUtil;

    public void sendMessage(Destination destination, final String msg) {
        logger.info("消息发送");
        //jmsMessagingTemplate.convertAndSend(destination, msg);
        sendQueue(msg);
    }

    public void sendQueue(String text) {
        //commonMessageUtil.convertAndSend(queue,"naonao");
    }
}
