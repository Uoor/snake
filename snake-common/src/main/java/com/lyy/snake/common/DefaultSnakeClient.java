package com.lyy.snake.common;

import com.lyy.snake.common.data.ConfigValueDTO;
import com.lyy.snake.common.data.SnakeDataCache;
import com.lyy.snake.common.listener.SnakeListener;
import com.lyy.snake.common.listener.SnakeListenerManager;
import com.lyy.snake.common.process.SnakeDataProcessor;

import java.util.Date;
import java.util.List;

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

    @Override
    public boolean addConfig(String domain, String key, String desc, String value) {
        try {
            ConfigValueDTO dto = new ConfigValueDTO();
            dto.setDomain(domain);
            dto.setKey(key);
            dto.setDesc(desc);
            dto.setValue(value);
            dto.setAddTime(new Date());
            return SnakeDataProcessor.addConfig(dto);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateConfig(String domain, String key,String desc, String value) {
        try {
            ConfigValueDTO dto = new ConfigValueDTO();
            dto.setDomain(domain);
            dto.setKey(key);
            dto.setDesc(desc);
            dto.setValue(value);
            dto.setAddTime(new Date());
            return SnakeDataProcessor.updateConfig(dto);
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean deleteConfig(String domain, String key) {
        try {
            return SnakeDataProcessor.deleteConfig(domain,key);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<String> keyList(String domain) {

        return null;
    }
}
