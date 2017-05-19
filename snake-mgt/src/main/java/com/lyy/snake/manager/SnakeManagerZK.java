package com.lyy.snake.manager;

import com.lyy.snake.common.dto.SnakeConfigInfoDTO;
import com.lyy.snake.common.serial.HessianSerial;
import com.lyy.snake.common.utils.ListUtils;
import com.lyy.snake.common.zk.SnakeZKPathManager;
import com.lyy.snake.common.zk.ZKInstence;
import com.lyy.snake.common.zk.ZKManager;
import org.apache.zookeeper.KeeperException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanshuai on 17/5/18.
 */
public class SnakeManagerZK implements SnakeManager{

    private ZKInstence zk;

    public SnakeManagerZK(String ZKClusterAddress,SnakeConfigCuratorWatcher snakeConfigCuratorWatcher){
        zk=ZKManager.getZKInstence(ZKClusterAddress);
        this.snakeConfigCuratorWatcher = snakeConfigCuratorWatcher;
    }
    public SnakeManagerZK(String ZKClusterAddress){
        zk=ZKManager.getZKInstence(ZKClusterAddress);
    }

    private SnakeConfigCuratorWatcher snakeConfigCuratorWatcher ;




    @Override
    public boolean setConfigValue(SnakeConfigInfoDTO snakeConfigInfo) throws Exception {
        if (snakeConfigInfo==null){
            return false;
        }
        String confitItemPath = SnakeZKPathManager.getConfigDescPath(snakeConfigInfo);
        String configValuePath = SnakeZKPathManager.getConfigValuePath(snakeConfigInfo);
        if (!zk.existsNode(confitItemPath,snakeConfigCuratorWatcher)){
            zk.create(confitItemPath, HessianSerial.serialize(snakeConfigInfo),snakeConfigCuratorWatcher);
        }
        if (!zk.existsNode(configValuePath,snakeConfigCuratorWatcher)){
            return zk.create(configValuePath, HessianSerial.serialize(snakeConfigInfo), snakeConfigCuratorWatcher);
        }else {
            byte[] oldValueData = zk.getData(configValuePath,snakeConfigCuratorWatcher);
            SnakeConfigInfoDTO oldValue = null;
            if (oldValueData==null){

            }else {
                oldValue =(SnakeConfigInfoDTO) HessianSerial.deserialize(oldValueData);
            }

            if (oldValue==null || oldValue.getVersion() == snakeConfigInfo.getVersion()){
                snakeConfigInfo.setVersion(snakeConfigInfo.getVersion()+1);
                return zk.setData(configValuePath, HessianSerial.serialize(snakeConfigInfo), snakeConfigCuratorWatcher);
            }else {
                return false;
            }
        }
    }



    @Override
    public List<SnakeConfigInfoDTO> getConfigItems(String domain, int startIndex, int limit) throws Exception {
        List<String> configNameList = null;
        try {
            configNameList = zk.getChildren(SnakeZKPathManager.getDomainPathForDesc(domain), snakeConfigCuratorWatcher);
        }catch (KeeperException.NoNodeException e){

        }
        configNameList = ListUtils.getSubList(startIndex, limit, configNameList);
        if (ListUtils.isEmpty(configNameList)){
            return null;
        }
        List<SnakeConfigInfoDTO> retValueList = new ArrayList<SnakeConfigInfoDTO>();
        for (String configName : configNameList){
            String path = SnakeZKPathManager.getConfigDescPath(domain, configName);
            SnakeConfigInfoDTO desc = getConfigDescValue(path);
            if (desc==null){
                continue;
            }
            SnakeConfigInfoDTO value = getConfigValue(SnakeZKPathManager.getConfigValuePath(desc));
            if (value!=null){
                desc.setConfigValue(value.getConfigValue());
                desc.setVersion(value.getVersion());
            }
            retValueList.add(desc);
        }
        return retValueList;
    }

    @Override
    public SnakeConfigInfoDTO getConfigInfo(SnakeConfigInfoDTO snakeConfigInfo) throws Exception {
        return getConfigValue(SnakeZKPathManager.getConfigValuePath(snakeConfigInfo));
    }

    @Override
    public boolean deleteConfigItem(SnakeConfigInfoDTO snakeConfigInfo) throws Exception {
        zk.delete(SnakeZKPathManager.getConfigValuePath(snakeConfigInfo));
        zk.delete(SnakeZKPathManager.getConfigDescPath(snakeConfigInfo));
        return true;
    }






    private SnakeConfigInfoDTO getConfigDescValue(String path) throws Exception {
        if (!path.startsWith(SnakeZKPathManager.CONFIG_ITEM_PATH_PREFIX)){
            throw new Exception( path +" not config item desc path");
        }
        byte[] data = zk.getData(path,snakeConfigCuratorWatcher);
        if (data==null || data.length==0){
            return null;
        }
        return (SnakeConfigInfoDTO) HessianSerial.deserialize(data);
    }

    private SnakeConfigInfoDTO getConfigValue(String path) throws Exception {
        if (!path.startsWith(SnakeZKPathManager.CONFIG_VALUE_PATH_PREFIX)){
            throw new Exception( path +" not config value path");
        }
        byte[] data = zk.getData(path,snakeConfigCuratorWatcher);
        if (data==null || data.length==0){
            return null;
        }
        return (SnakeConfigInfoDTO) HessianSerial.deserialize(data);
    }

}
