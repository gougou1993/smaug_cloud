package smaug.cloud.provider.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smaug.cloud.common.utils.jsons.FastJsonUtil;

/**
 * Created by naonao on 17/10/14.
 */
public class AbstractService {
    protected FastJsonUtil jsonUtil = new FastJsonUtil();

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}