package com.lyy.snake.client;

import com.lyy.snake.client.data.ConfigValueDTO;
import com.lyy.snake.client.data.SnakeDataCache;
import com.lyy.snake.client.listener.SnakeListener;
import com.lyy.snake.client.listener.SnakeListenerManager;
import com.lyy.snake.client.process.SnakeDataProcessor;

/**
 * Created by fanshuai on 17/5/8.
 */
public class DefaultSnakeClient implements SnakeClient{
    @Override
    public String getConfig(String domain,String key) {
        ConfigValueDTO configValueDTO=SnakeDataCache.getCacheValue(domain,key);
        if (configValueDTO==null){
            try {
                configValueDTO = SnakeDataProcessor.getConfigValue(domain,key);
                SnakeDataCache.setCacheValue(domain,key,configValueDTO);
                return configValueDTO.getValue();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return configValueDTO.getValue();
    }

    @Override
    public String getConfig(String domain,String key, String defaultValue) {
        String value = getConfig(domain,key);
        if (value==null){
            return defaultValue;
        }
        return value;
    }

    @Override
    public String getConfig(String domain,String key, String defaultValue, SnakeListener snakeListener) {
        SnakeListenerManager.setListener(domain,key,snakeListener);
        return getConfig(key,defaultValue);
    }

    @Override
    public String getConfig(String domain,String key, SnakeListener snakeListener) {
        SnakeListenerManager.setListener(domain,key,snakeListener);
        return getConfig(domain,key);
    }

    public static void main(String args[]){

    }
}
