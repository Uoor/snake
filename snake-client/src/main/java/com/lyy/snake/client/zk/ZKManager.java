package com.lyy.snake.client.zk;

import com.lyy.snake.client.env.EnvManager;
import com.lyy.snake.client.exceptions.ZKNotConnectionException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 17/5/8.
 */
public class ZKManager {
    private static Map<String,ZKInstence> zkInstenceMap = new HashMap<String, ZKInstence>();
    public static ZKInstence getZKInstence(){
        String host = EnvManager.getCurEnvZKHost();
        ZKInstence instence = zkInstenceMap.get(host);
        if (instence==null){
            synchronized (host){
                instence = zkInstenceMap.get(host);
                if (instence==null){
                    try {
                        instence = new ZKInstence(host,new SnakeCuratorListener(host),new SnakeConnectionStateListener(host));
                    } catch (ZKNotConnectionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    zkInstenceMap.put(host,instence);
                }
            }
        }
        return instence;
    }
}
