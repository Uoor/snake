package com.lyy.snake.client.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;

/**
 * Created by fanshuai on 17/5/9.
 */
public class SnakeConnectionStateListener implements ConnectionStateListener {
    private String zkClusterHost;
    public SnakeConnectionStateListener(String zkClusterHost){
        this.zkClusterHost = zkClusterHost;
    }
    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

    }
}
