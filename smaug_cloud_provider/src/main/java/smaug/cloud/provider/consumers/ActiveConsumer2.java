package smaug.cloud.provider.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * author Allen
 * 用于消息的消费
 * 一般的项目, 消息生产者和消费者应该分开,这里为了方便就写在一起了
 *写第二个消费者.
 * @since 2017-11-06 下午4:17
 */
@Component
public class ActiveConsumer2 {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        logger.info("Consumer2收到的报文为:" + text);
    }
}
