package smaug.cloud.common.utils.jsons;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by naonao on 17/10/14.
 */
public class FastJsonUtil implements JsonUtil {
    @Override
    public String toJson(Object o) {
        if (null == o) {
            return null;
        }
        return JSONObject.toJSONString(o);
    }
}
