package com.lyy.snake.common;

import com.lyy.snake.common.listener.SnakeListener;

import java.util.List;

/**
 * Created by fanshuai on 17/5/8.
 */
public interface SnakeClient {
    /**
     * 获得配置信息
     * @param key
     * @return
     */
    public String getConfig(String domain, String key);

    /**
     * 获得配置信息
     * @param key
     * @param defaultValue
     * @return
     */
    public String getConfig(String domain, String key, String defaultValue);

    /**
     * 获得配置信息
     * @param key
     * @param defaultValue
     * @param snakeListener
     * @return
     */
    public String getConfig(String domain, String key, String defaultValue, SnakeListener snakeListener);

    public String getConfig(String domain, String key, SnakeListener snakeListener);

    public boolean addConfig(String domain, String key, String desc, String value);

    public boolean updateConfig(String domain, String key, String desc, String value);

    public boolean deleteConfig(String domain, String key);

    public List<String> keyList(String domain);

}
