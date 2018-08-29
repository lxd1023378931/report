package com.uzak.service.util;

/**
 * Created by Administrator on 2018/8/30.
 */
public class StringUtil {

    public static boolean isBlank(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

}
