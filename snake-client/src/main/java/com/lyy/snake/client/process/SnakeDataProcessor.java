package com.lyy.snake.client.process;

import com.lyy.snake.client.data.ConfigValueDTO;
import com.lyy.snake.client.data.SnakeConfigKey;
import com.lyy.snake.client.data.SnakeDataCache;
import com.lyy.snake.client.serial.HessianSerial;
import com.lyy.snake.client.zk.ZKInstence;
import com.lyy.snake.client.zk.ZKManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by fanshuai on 17/5/9.
 */
public class SnakeDataProcessor{
    public static final String propertyBaseZKNode ="/config/";
    private static ZKInstence zkInstence = ZKManager.getZKInstence();

    private static String keyToPath(String domain,String key){
        return propertyBaseZKNode+domain+"/"+key;
    }
    private static SnakeConfigKey pathToKey(String path){
        if (path.startsWith(propertyBaseZKNode)){
            String pathSub[] = path.substring(propertyBaseZKNode.length(),path.length()).split("/");
            if (pathSub==null || pathSub.length!=2){
                return null;
            }
            SnakeConfigKey key = new SnakeConfigKey();
            key.setDomain(pathSub[0]);
            key.setKey(pathSub[1]);
            return key;
        }
        return null;
    }

    public static ConfigValueDTO getConfigValue(String domain,String configKey) throws Exception {
        String path = keyToPath(domain,configKey);
        byte[] data = zkInstence.getData(path,null);
        if (data == null){
            ConfigValueDTO configValueDTO = new ConfigValueDTO();
            configValueDTO.setDomain(domain);
            configValueDTO.setKey(configKey);
            configValueDTO.setValue(null);
            return configValueDTO;
        }
//        Object o = HessianSerial.deserialize(data);
        Object o = new String(data);
        if (o instanceof ConfigValueDTO){
            return (ConfigValueDTO)o;
        }
        ConfigValueDTO configValueDTO = new ConfigValueDTO();
        configValueDTO.setDomain(domain);
        configValueDTO.setKey(configKey);
        configValueDTO.setValue(o.toString());
        return configValueDTO;
    }
    public static void flushCacheValue(String path){
        try {
            SnakeConfigKey key = pathToKey(path);
            ConfigValueDTO configValueDTO = getConfigValue(key.getDomain(), key.getKey());
            SnakeDataCache.setCacheValue(key.getDomain(),key.getKey(),configValueDTO);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
