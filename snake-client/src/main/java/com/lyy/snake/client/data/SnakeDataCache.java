package com.lyy.snake.client.data;

import com.lyy.snake.client.listener.SnakeListener;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fanshuai on 17/5/8.
 */
public class SnakeDataCache {
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
