package smaug.cloud.common.utils.str;

/**
 * Allen
 * 17/11/20
 **/
public class StringUtil {
    /**
     * 字符串是否为null or ""
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }
}
