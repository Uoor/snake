package com.lyy.snake.common.data;

import com.lyy.snake.common.dto.SnakeConfigInfoDTO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fanshuai on 17/5/8.
 */
public class SnakeDataCache {
    private static Map<String,SnakeConfigInfoDTO> snakeConfigInfoLocalCache = new ConcurrentHashMap<String, SnakeConfigInfoDTO>();

    public static SnakeConfigInfoDTO getValueFromLocalCache(String domain,String configKey){
        String key = domain+"_"+configKey;
        return snakeConfigInfoLocalCache.get(key);
    }
    public static void  setValueToLocalCache(String domain,String configKey,SnakeConfigInfoDTO configValueDTO){
        String key = domain+"_"+configKey;
        snakeConfigInfoLocalCache.put(key, configValueDTO);
    }


    private static Map<String,ConfigValueDTO> dataCache = new ConcurrentHashMap<String, ConfigValueDTO>();
    public static ConfigValueDTO getCacheValue(String domain,String configKey){
        String key = domain+"_"+configKey;
        return dataCache.get(key);
    }
    public static void  setCacheValue(String domain,String configKey,ConfigValueDTO configValueDTO){
        String key = domain+"_"+configKey;
        dataCache.put(key,configValueDTO);
    }
}
