package com.lyy.snake.common.zk;

import com.lyy.snake.common.exceptions.ZKNotConnectionException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Created by fanshuai on 17/5/8.
 */
public class ZKInstence {
    private String zkClusterHost;
    private CuratorFramework curatorFramework ;
    private CuratorListener curatorListener;
    private ConnectionStateListener connectionStateListener;
    public ZKInstence(String zkClusterHost) throws ZKNotConnectionException, InterruptedException {
        this.zkClusterHost = zkClusterHost;
        init();
    }

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    public ZKInstence(String zkClusterHost,CuratorListener curatorListener,ConnectionStateListener connectionStateListener) throws ZKNotConnectionException, InterruptedException {
        this.zkClusterHost = zkClusterHost;
        this.curatorListener = curatorListener;
        this.connectionStateListener = connectionStateListener;
        init();
    }
    private void init() throws InterruptedException,ZKNotConnectionException {
        curatorFramework = CuratorFrameworkFactory.newClient(zkClusterHost, new ExponentialBackoffRetry(1000, 3));
        if (curatorListener!=null){
            curatorFramework.getCuratorListenable().addListener(curatorListener);
        }
        if (connectionStateListener!=null){
            curatorFramework.getConnectionStateListenable().addListener(connectionStateListener);
        }
        curatorFramework.start();
        boolean hasConnected = curatorFramework.getZookeeperClient().blockUntilConnectedOrTimedOut();
        if (!hasConnected){
            throw new ZKNotConnectionException();
        }
    }

    public byte[] getData(String path,CuratorWatcher watcher) throws Exception {
        if (!existsNode(path,watcher)){
            return null;
        }
        if(watcher!=null) {
            return curatorFramework.getData().usingWatcher(watcher).forPath(path);
        }else {
            return curatorFramework.getData().watched().forPath(path);
        }
    }

    public boolean existsNode(String pathNode,CuratorWatcher watcher) throws Exception {
        Stat stat = null;
        if (watcher!=null){
            stat=curatorFramework.checkExists().usingWatcher(watcher).forPath(pathNode);
        }else {
            stat = curatorFramework.checkExists().watched().forPath(pathNode);
        }
        if(stat==null){
            return false;
        }
        return true;
    }

    public boolean create(String path,byte[] value,CuratorWatcher watcher) throws Exception {
        if (existsNode(path, watcher)){
            return false;
        }
        String stat = curatorFramework.create().creatingParentsIfNeeded().forPath(path,value);
        return true;
    }

    public boolean setData(String path,byte[] value,CuratorWatcher watcher) throws Exception {
        Stat stat=curatorFramework.setData().forPath(path,value);
        if (stat==null){
            return false;
        }
        return true;
    }

    public boolean delete(String path) throws Exception {
        curatorFramework.delete().forPath(path);
        return true;
    }

    public List<String> getChildren(String path,CuratorWatcher watcher) throws Exception {
        List<String> childrenPath = null;
        if (watcher!=null){
            childrenPath = curatorFramework.getChildren().usingWatcher(watcher).forPath(path);
        }else {
            childrenPath = curatorFramework.getChildren().watched().forPath(path);
        }
        return childrenPath;
    }

}
