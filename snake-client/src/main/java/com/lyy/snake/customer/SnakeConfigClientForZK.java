package com.lyy.snake.customer;

import com.lyy.snake.common.data.SnakeDataCache;
import com.lyy.snake.common.dto.SnakeConfigInfoDTO;
import com.lyy.snake.common.serial.HessianSerial;
import com.lyy.snake.common.zk.SnakeZKPathManager;
import com.lyy.snake.common.zk.ZKInstence;
import com.lyy.snake.common.zk.ZKManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fanshuai on 17/5/19.
 */
public class SnakeConfigClientForZK implements SnakeConfigClient{
    private static final Logger log = LoggerFactory.getLogger(SnakeConfigClientForZK.class);
    private String ZKClusterAddress ;
    private ZKInstence zk;
    private SnakeConfigCuratorWatcher snakeConfigCuratorWatcher ;
    public SnakeConfigClientForZK(String ZKClusterAddress){
        this.ZKClusterAddress = ZKClusterAddress;
        zk = ZKManager.getZKInstence(ZKClusterAddress);
        this.snakeConfigCuratorWatcher = new SnakeConfigCuratorWatcher(zk);
    }
    @Override
    public String getConfigValue(String domain, String configName) {
        try {
            SnakeConfigInfoDTO cacheValue = SnakeDataCache.getValueFromLocalCache(domain, configName);
            if (cacheValue!=null){
                return cacheValue.getConfigValue();
            }
            cacheValue = getValueFromRemoteZK(domain,configName);
            if (cacheValue==null){
                cacheValue=new SnakeConfigInfoDTO();
                cacheValue.setDomain(domain);
                cacheValue.setConfigName(configName);
            }
            SnakeDataCache.setValueToLocalCache(domain,configName,cacheValue);
            return cacheValue.getConfigValue();
        }catch (Exception e){
            log.error("getConfigValue exception "+e.getMessage(),e);
            return null;
        }
    }
    private SnakeConfigInfoDTO getValueFromRemoteZK(String domain, String configName) throws Exception {
        SnakeConfigInfoDTO cacheValue = new SnakeConfigInfoDTO();
        cacheValue.setDomain(domain);
        cacheValue.setConfigName(configName);
        String path = SnakeZKPathManager.getConfigValuePath(domain,configName);
        byte[] data = zk.getData(path,snakeConfigCuratorWatcher);
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
