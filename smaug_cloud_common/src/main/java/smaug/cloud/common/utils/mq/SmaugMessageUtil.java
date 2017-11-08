package smaug.cloud.common.utils.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.List;

/**
 * mq 消息 工具类
 */
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

}
