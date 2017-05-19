package com.lyy.snake.customer;

import com.lyy.snake.common.env.EnvManager;

/**
 * Created by fanshuai on 17/5/19.
 */
public class SnakeConfigClientFactory {
    private static SnakeConfigClient defaultSnakeConfigClient;
    public static SnakeConfigClient getDefaultSnakeConfigClient(){
        if (defaultSnakeConfigClient !=null ){
            return defaultSnakeConfigClient;
        }
        synchronized (SnakeConfigClientFactory.class){
            if (defaultSnakeConfigClient != null){
                return defaultSnakeConfigClient;
            }
            defaultSnakeConfigClient = new SnakeConfigClientForZK(EnvManager.getCurEnvZKHost());
        }
        return defaultSnakeConfigClient;
    }
}
