package com.lyy.snake.client;

import com.lyy.snake.client.listener.SnakeListener;

/**
 * Created by fanshuai on 17/5/8.
 */
public interface SnakeClient {
    /**
     * 获得配置信息
     * @param key
     * @return
     */
    public String getConfig(String domain,String key);

    /**
     * 获得配置信息
     * @param key
     * @param defaultValue
     * @return
     */
    public String getConfig(String domain,String key,String defaultValue);

    /**
     * 获得配置信息
     * @param key
     * @param defaultValue
     * @param snakeListener
     * @return
     */
    public String getConfig(String domain,String key,String defaultValue,SnakeListener snakeListener);

    public String getConfig(String domain,String key ,SnakeListener snakeListener);
}
