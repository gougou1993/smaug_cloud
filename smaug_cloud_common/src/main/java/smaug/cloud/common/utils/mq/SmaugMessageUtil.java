package smaug.cloud.common.utils.mq;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.Collections;
import java.util.List;

/**
 * mq 消息 工具类
 */
@Getter
@Setter
public final class SmaugMessageUtil {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 连接工厂设置
     */
    private ConnectionFactory connectionFactory;
    /**
     * 接受超时时间
     */
    private long receiveTimeOut;

    private JmsTemplate jmsTemplate;

    /**
     * 目标队列
     */
    private List<String> destinationQueueNames;

    private final static String BACKUP_QUEUE_SUFFIX = "_B";

    /**
     * 是否备份到备份队列， 方便调试
     */
    private boolean autoBackUp = false;

    public SmaugMessageUtil(final ConnectionFactory connectionFactory, final long receiveTimeOut, final List<String> destinationQueueNames) {
        this.connectionFactory = connectionFactory;
        this.receiveTimeOut = receiveTimeOut;
        this.destinationQueueNames = destinationQueueNames;
        jmsTemplate = new JmsTemplate(this.connectionFactory);
        jmsTemplate.setReceiveTimeout(receiveTimeOut);
    }

    public SmaugMessageUtil(final ConnectionFactory connectionFactory, List<String> destinationQueueNames) {
        this(connectionFactory, 10000, destinationQueueNames);
    }

    public void convertAndSend(Object message) {
        if (Collections.EMPTY_LIST == destinationQueueNames) {
            logger.info("目标队列为空");
            return;
        }
        for (String dest : destinationQueueNames) {
            jmsTemplate.convertAndSend(dest, message);
            if (autoBackUp) {
                jmsTemplate.convertAndSend(dest+"_B", message);
            }
        }
    }
}
