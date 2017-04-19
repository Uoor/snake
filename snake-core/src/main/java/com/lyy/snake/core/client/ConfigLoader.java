package com.lyy.snake.core.client;

import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 16/7/2.
 */
public interface ConfigLoader {
    String getConfigValue(String key) throws Exception;

    boolean setConfigvalue(String key, String value) throws Exception;
    public boolean deleteConfig(String key) throws Exception ;
    Map<String,String> getDomainConfigValues(String domain) throws Exception;

    public void addConfigChangeListener(ConfigChangeListener configChangeListener);

    List<String> getAllDomains() throws Exception;
}
