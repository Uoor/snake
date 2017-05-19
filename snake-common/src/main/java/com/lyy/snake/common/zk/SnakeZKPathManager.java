package com.lyy.snake.common.zk;

import com.lyy.snake.common.dto.SnakeConfigInfoDTO;

/**
 * Created by fanshuai on 17/5/19.
 */
public class SnakeZKPathManager {
    public static final String CONFIG_ITEM_PATH_PREFIX = "/snake/config/item";
    public static final String CONFIG_VALUE_PATH_PREFIX = "/snake/config/value";
    public static String getDomainPathForDesc(String domain){
        return CONFIG_ITEM_PATH_PREFIX+"/"+domain;
    }
    public String getDomainPathForDesc(SnakeConfigInfoDTO snakeConfigInfo){
        return CONFIG_ITEM_PATH_PREFIX+"/"+snakeConfigInfo.getDomain();
    }

    public static String getDomainPathForValue(SnakeConfigInfoDTO snakeConfigInfo){
        return CONFIG_ITEM_PATH_PREFIX+"/"+snakeConfigInfo.getDomain();
    }

    public static String getConfigDescPath(SnakeConfigInfoDTO snakeConfigInfo){
        return CONFIG_ITEM_PATH_PREFIX+"/"+snakeConfigInfo.getDomain()+"/"+snakeConfigInfo.getConfigName();
    }
    public static String getConfigDescPath(String domain,String configName){
        return CONFIG_ITEM_PATH_PREFIX+"/"+domain+"/"+configName;
    }

    public static String getConfigValuePath(SnakeConfigInfoDTO snakeConfigInfo){
        return CONFIG_VALUE_PATH_PREFIX+"/"+snakeConfigInfo.getDomain()+"/"+snakeConfigInfo.getConfigName();
    }
    public static String getConfigValuePath(String domain, String configName){
        return CONFIG_VALUE_PATH_PREFIX+"/"+domain+"/"+configName;
    }

    public static String getConfigName(String path){
        if (path.startsWith(CONFIG_ITEM_PATH_PREFIX)){
            String pathSub[] = path.substring(CONFIG_ITEM_PATH_PREFIX.length()+1,path.length()).split("/");
            if (pathSub==null || pathSub.length!=2){
                return null;
            }
            return pathSub[1];
        }else if (path.startsWith(CONFIG_VALUE_PATH_PREFIX)){
            String pathSub[] = path.substring(CONFIG_VALUE_PATH_PREFIX.length()+1,path.length()).split("/");
            if (pathSub==null || pathSub.length!=2){
                return null;
            }
            return pathSub[1];
        }
        return null;
    }
    public static String getDomain(String path){
        if (path.startsWith(CONFIG_ITEM_PATH_PREFIX)){
            String pathSub[] = path.substring(CONFIG_ITEM_PATH_PREFIX.length()+1,path.length()).split("/");
            if (pathSub==null || pathSub.length!=2){
                return null;
            }
            return pathSub[0];
        }else if (path.startsWith(CONFIG_VALUE_PATH_PREFIX)){
            String pathSub[] = path.substring(CONFIG_VALUE_PATH_PREFIX.length()+1,path.length()).split("/");
            if (pathSub==null || pathSub.length!=2){
                return null;
            }
            return pathSub[0];
        }
        return null;
    }
    public static boolean isSnakeConfigPath(String path){
        if (path.startsWith(CONFIG_ITEM_PATH_PREFIX)){
            return true;
        }else if (path.startsWith(CONFIG_VALUE_PATH_PREFIX)){
            return true;
        }
        return false;
    }

}
