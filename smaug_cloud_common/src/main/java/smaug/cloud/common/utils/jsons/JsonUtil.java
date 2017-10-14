package smaug.cloud.common.utils.jsons;

import org.springframework.stereotype.Component;

/**
 * Created by naonao on 17/10/14.
 */
@Component("jsonUtil")
public interface JsonUtil {
    String toJson(Object o);
}
