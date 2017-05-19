package com.lyy.snake.manager;

import com.lyy.snake.common.env.EnvManager;

/**
 * Created by fanshuai on 17/5/18.
 */
public class SnakeMamagerFactory {
    private static SnakeManager snakeManager;
    public static SnakeManager getDefaultSnakeManager(){
        if (snakeManager !=null ){
            return snakeManager;
        }
        synchronized (SnakeMamagerFactory.class){
            if (snakeManager != null){
                return snakeManager;
            }
            snakeManager = new SnakeManagerZK(EnvManager.getCurEnvZKHost());
        }
        return snakeManager;
    }
}
