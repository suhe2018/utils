package com.puhui.dc.utils.utils;

/**
 * Created by rongjianmin on 2015/7/21.
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * 
     * @author rongjianmin
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        boolean flag = false;
        if (null == str || "".equals(str)) {
            flag = true;
        }
        return flag;
    }
}
