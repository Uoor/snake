package com.lyy.snake.customer;

/**
 * Created by fanshuai on 17/5/19.
 */
public interface SnakeConfigClient {

    String getConfigValue(String domain,String configName);
}
