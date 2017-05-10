package com.lyy.snake.client.zk;

import com.lyy.snake.client.process.SnakeDataProcessor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.Watcher;

/**
 * Created by fanshuai on 17/5/9.
 */
public class SnakeCuratorListener implements CuratorListener {
    private String zkClusterHost;
    public SnakeCuratorListener(String zkClusterHost){
        this.zkClusterHost = zkClusterHost;
    }
    @Override
    public void eventReceived(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
        String path = curatorEvent.getPath();
        switch (curatorEvent.getWatchedEvent().getType()){
            case NodeCreated:{
                System.out.println("NodeCreated:"+path);
                SnakeDataProcessor.flushCacheValue(path);
                break;
            }
            case NodeDeleted:{
                System.out.println("NodeDeleted:"+path);
                SnakeDataProcessor.flushCacheValue(path);
                break;
            }
            case NodeDataChanged:
            {
                System.out.println("NodeDataChanged:"+path);
                SnakeDataProcessor.flushCacheValue(path);
                break;
            }
            default:break;
        }
    }
}
