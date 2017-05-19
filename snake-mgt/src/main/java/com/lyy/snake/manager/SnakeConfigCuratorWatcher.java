package com.lyy.snake.manager;

import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

/**
 * Created by fanshuai on 17/5/18.
 */
public class SnakeConfigCuratorWatcher implements CuratorWatcher {
    @Override
    public void process(WatchedEvent event) throws Exception {

    }
}
