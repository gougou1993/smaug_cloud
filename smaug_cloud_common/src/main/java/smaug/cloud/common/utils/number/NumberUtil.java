package smaug.cloud.common.utils.number;

import org.apache.commons.lang.StringUtils;

/**
 * Allen
 * 17/11/20
 **/
public class NumberUtil {
    public static int getIntValue(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        return Integer.valueOf(str).intValue();
    }

    /**
     * 字符串转数字
     * @param str
     * @param defaultValue
     * @return
     */
    public static int getIntValue(String str, int defaultValue) {
        if (StringUtils.isBlank(str)) {
            return defaultValue;
        }
        return Integer.valueOf(str).intValue();
    }
}
