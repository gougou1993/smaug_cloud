package smaug.cloud.provider.impl;

import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import smaug.cloud.common.consts.MQConst;
import smaug.cloud.common.utils.cache.SmaugJedisUtil;
import smaug.cloud.common.utils.jsons.FastJsonUtil;
import smaug.cloud.common.utils.mq.SmaugMessageUtil;

import javax.annotation.Resource;

/**
 * Created by naonao on 17/10/14.
 */
public class AbstractService {


    @Resource(name = "smaugJedisUtil")
    protected SmaugJedisUtil smaugJedisUtil;

    protected FastJsonUtil jsonUtil = new FastJsonUtil();

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected MQConst mqConst;

    @Resource(name = "commonMessageUtil")
    protected SmaugMessageUtil commonMessageUtil;
}
