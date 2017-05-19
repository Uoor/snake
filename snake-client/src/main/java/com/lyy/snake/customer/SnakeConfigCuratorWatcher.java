package com.lyy.snake.customer;

import com.lyy.snake.common.data.SnakeDataCache;
import com.lyy.snake.common.dto.SnakeConfigInfoDTO;
import com.lyy.snake.common.process.SnakeDataProcessor;
import com.lyy.snake.common.serial.HessianSerial;
import com.lyy.snake.common.zk.SnakeZKPathManager;
import com.lyy.snake.common.zk.ZKInstence;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * Created by fanshuai on 17/5/18.
 */
public class SnakeConfigCuratorWatcher implements CuratorWatcher {
    private ZKInstence zk;
    public SnakeConfigCuratorWatcher(ZKInstence zk){
        this.zk = zk;
    }
    @Override
    public void process(WatchedEvent event) throws Exception {
        String path = event.getPath();
        if (!path.startsWith(SnakeZKPathManager.CONFIG_VALUE_PATH_PREFIX)){
            return;
        }
        switch (event.getType()){
            case NodeCreated:{
                System.out.println("NodeCreated:"+path);
                flushConfigValue(path);
                break;
            }
            case NodeDeleted:{
                System.out.println("NodeDeleted:"+path);
                flushConfigValue(path);
                break;
            }
            case NodeDataChanged:
            {
                System.out.println("NodeDataChanged:"+path);
                flushConfigValue(path);
                break;
            }
            default:break;
        }
    }

    public  void flushConfigValue(String path){
        if (!SnakeZKPathManager.isSnakeConfigPath(path)){
            return;
        }
        String domain = SnakeZKPathManager.getDomain(path);
        String configName = SnakeZKPathManager.getConfigName(path);
        try {
            SnakeConfigInfoDTO snakeConfigInfoDTO = getValueFromRemoteZK(domain,configName);
            if (snakeConfigInfoDTO==null){
                snakeConfigInfoDTO =  new SnakeConfigInfoDTO();
                snakeConfigInfoDTO.setDomain(domain);
                snakeConfigInfoDTO.setConfigName(configName);
            }
            SnakeDataCache.setValueToLocalCache(domain,configName,snakeConfigInfoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SnakeConfigInfoDTO getValueFromRemoteZK(String domain, String configName) throws Exception {
        SnakeConfigInfoDTO cacheValue = new SnakeConfigInfoDTO();
        cacheValue.setDomain(domain);
        cacheValue.setConfigName(configName);
        String path = SnakeZKPathManager.getConfigValuePath(domain,configName);
        byte[] data = zk.getData(path,this);
        if (data==null || data.length==0){
            return null;
        }
        SnakeConfigInfoDTO remoteValue = (SnakeConfigInfoDTO) HessianSerial.deserialize(data);
        if (remoteValue==null){
            return null;
        }
        cacheValue.setConfigValue(remoteValue.getConfigValue());
        cacheValue.setVersion(remoteValue.getVersion());
        return cacheValue;
    }
}
