package com.lyy.snake.spring;

import com.google.common.base.Strings;

/**
 * Created by fanshuai on 16/7/2.
 */
public class ConfigKeyUtils {
    private static final String prefix = "${";
    private static final String suffix = "}";
    public static boolean isDynamicConfig(String key){
        if (Strings.isNullOrEmpty(key)){
            return false;
        }
        key = key.trim();
        if (key.startsWith(prefix)&&key.endsWith(suffix)){
            return true;
        }
        return false;
    }

    public static String getConfigKeyFromDynamicKey(String dynamicKey){
        return dynamicKey.substring(dynamicKey.indexOf(prefix)+prefix.length(),dynamicKey.indexOf(suffix));
    }

}
