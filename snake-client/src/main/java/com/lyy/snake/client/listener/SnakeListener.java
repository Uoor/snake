package com.lyy.snake.client.listener;

/**
 * Created by fanshuai on 17/5/8.
 */
public interface SnakeListener {
    public void onKeyUpdated(String domain,String key,String newValue);
    public void onKeyDeleted(String domain,String key);
    public void onKeyCreated(String domain,String key,String value);
}
