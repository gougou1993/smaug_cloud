package smaug.cloud.provider.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import smaug.cloud.common.utils.jsons.FastJsonUtil;
import smaug.cloud.common.utils.jsons.JsonUtil;

/**
 * Created by naonao on 17/10/15.
 */
public class AbstractHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected JsonUtil jsonUtil = new FastJsonUtil();

}
