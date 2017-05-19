package com.lyy.snake.common.zk;

import com.lyy.snake.common.env.EnvManager;
import com.lyy.snake.common.exceptions.ZKNotConnectionException;

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
                        instence = new ZKInstence(host);
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

    public static ZKInstence getZKInstence(String ZKClusterAddress){
        ZKInstence instence = zkInstenceMap.get(ZKClusterAddress);
        if (instence==null){
            synchronized (ZKClusterAddress){
                instence = zkInstenceMap.get(ZKClusterAddress);
                if (instence==null){
                    try {
                        instence = new ZKInstence(ZKClusterAddress);
                    } catch (ZKNotConnectionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    zkInstenceMap.put(ZKClusterAddress,instence);
                }
            }
        }
        return instence;
    }
}
