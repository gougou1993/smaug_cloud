package smaug.cloud.common.utils.zk;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import smaug.cloud.common.utils.str.StringUtil;

import java.util.List;

/**
 * Allen
 * 17/11/20
 * 基于zookeeper 的分布式锁
 **/
public abstract class AbstractLock {
    private int lockNumber = 1; //允许获取锁的数量 最小为1 当最小节点 = 自身的时候 即认为获取到锁
    private ZkClient zkClient = null;
    private String rootNode = "/smaug/node/";
    private String selfNode;
    private final String className = this.getClass().getName(); //当前示例的className
    private String selfNodeName; //自身注册的临时节点名字
    private boolean handing = false;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String SPLIT = "/";
    private String selfNodeFullName;


    /**
     * 初始化节点 先查看根目录 如果没有根目录 则注册 再查看实例， 如果未注册， 进行注册操作
     */
    private void initNode() {
        String error;
        if (!rootNode.startsWith(SPLIT)) {
            error = "rootNode必须以" + SPLIT + "开头";
            throw new RuntimeException(error);
        }
        if (!rootNode.endsWith(SPLIT)) {
            error = "rootNode必须以" + SPLIT + "结尾";
            throw new RuntimeException(error);
        }
        int start = 1;
        int index = rootNode.indexOf(SPLIT, start);
        String path;
        while (index != -1) {
            path = rootNode.substring(0, index);
            if (!zkClient.exists(path)) {
                zkClient.createPersistent(path);
            }
            start = index + 1;
            if (start > rootNode.length()) {
                break;
            }
            index = rootNode.indexOf(SPLIT, start);
        }
        if (start < rootNode.length()) {
            if (!zkClient.exists(rootNode)) {
                zkClient.createPersistent(rootNode);
            }
        }
        selfNode = rootNode + SPLIT + className;
        if (!zkClient.exists(selfNode)) {
            zkClient.createPersistent(selfNode);
        }
    }

    /**
     * 注册
     */
    private void register() {
        selfNodeName = zkClient.createEphemeralSequential(selfNode + "/", "");
        if (!StringUtil.isEmpty(selfNodeName)) {
            selfNodeFullName = selfNodeName;
            logger.info("自身节点 : " + selfNodeFullName + "注册成功");
            selfNodeName = selfNodeName.substring(selfNode.length() + 1);
        }
    }

    /**
     * 检测是否获取到锁
     */
    private void checkMin() {
        List<String> list = zkClient.getChildren(selfNode);
        if (CollectionUtils.isEmpty(list)) {
            logger.error(selfNode + "  下无任何子节点");
            lockFail();
            return;
        }
        int max = Math.min(getLockNumber(), list.size());
        for (int i = 0; i < max; i++) {
            if (list.get(i).equals(selfNodeName)) {
                if (!handing) {
                    handing = true;
                    lockSuccess();
                    logger.info(selfNode + " 获取锁成功");
                }
                return;
            }
        }
    }

    /**
     * 获取锁失败 由各个子类去重写
     */
    private void lockFail() {

    }

    private void lockSuccess() {

    }

    private int getLockNumber() {
        return this.lockNumber;
    }
}
