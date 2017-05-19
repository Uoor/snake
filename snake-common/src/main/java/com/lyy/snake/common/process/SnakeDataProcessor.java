package com.lyy.snake.common.process;

import com.lyy.snake.common.data.ConfigValueDTO;
import com.lyy.snake.common.data.SnakeConfigKey;
import com.lyy.snake.common.data.SnakeDataCache;
import com.lyy.snake.common.serial.HessianSerial;
import com.lyy.snake.common.zk.ZKInstence;
import com.lyy.snake.common.zk.ZKManager;

import java.util.Date;
import java.util.List;

/**
 * Created by fanshuai on 17/5/9.
 */
public class SnakeDataProcessor{
    public static final String propertyBaseZKNode ="/config/";
    private static ZKInstence zkInstence = ZKManager.getZKInstence();

    private static String getKeyPath(String domain, String key){
        return getDomainPath(domain)+"/"+key;
    }
    private static String getDomainPath(String domain){
        return propertyBaseZKNode+domain;
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
        String path = getKeyPath(domain, configKey);
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

    public static boolean addConfig(ConfigValueDTO configValueDTO)  {

        try {
            if (configValueDTO==null){
                return false;
            }
            configValueDTO.setAddTime(new Date());
            String pathNode = getKeyPath(configValueDTO.getDomain(),configValueDTO.getKey());
            if (zkInstence.existsNode(pathNode,null)){
                return false;
            }else {
                return zkInstence.create(pathNode,HessianSerial.serialize(configValueDTO),null);
            }
        }catch (Exception e){
            return false;
        }
    }

    public static boolean updateConfig(ConfigValueDTO configValueDTO) {
        try {
            String pathNode = getKeyPath(configValueDTO.getDomain(),configValueDTO.getKey());
            if (!zkInstence.existsNode(pathNode,null)){
                return false;
            }
            return zkInstence.setData(pathNode,HessianSerial.serialize(configValueDTO),null);
        }catch (Exception e){
            return false;
        }
    }

    public static boolean deleteConfig(String domain, String key) throws Exception {
        return zkInstence.delete(getKeyPath(domain,key));
    }
    public List<String> keyList(String domain) throws Exception {
        List<String> pathList = zkInstence.getChildren(getDomainPath(domain),null);
        if (pathList==null){
            return null;
        }
        return null;
    }

}
