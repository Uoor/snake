package com.lyy.snake.client.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by fanshuai on 17/5/8.
 */
public class SnakeListenerManager {
    private static Map<String,List<SnakeListener>> keyListeners = new ConcurrentHashMap<String, List<SnakeListener>>();
    public static void setListener(String domain,String configKey,SnakeListener snakeListener){
        if (domain == null || configKey==null || snakeListener==null){
            return;
        }
        String key = domain+"_"+configKey;
        List<SnakeListener> listeners = keyListeners.get(key);
        if (listeners==null){
            synchronized (key){
                listeners = keyListeners.get(key);
                if (listeners==null){
                    listeners = Collections.synchronizedList(new ArrayList<SnakeListener>());
                    keyListeners.put(key,listeners);
                }
            }
        }
        if (listeners.contains(snakeListener)){
            return;
        }
        synchronized (snakeListener){
            if (listeners.contains(snakeListener)){
                return;
            }
            listeners.add(snakeListener);
        }
    }
    public static List<SnakeListener> getListeners(String domain,String configKey){
        String key = domain+"_"+configKey;
        return keyListeners.get(key);
    }
}
