package com.lyy.snake.manager;

import com.lyy.snake.common.dto.SnakeConfigInfoDTO;

import java.util.List;

/**
 * Created by fanshuai on 17/5/18.
 */
public interface SnakeManager {
    /**
     * 设置配置信息,配置不存在时添加，已存在时修改
     * @param snakeConfigInfo
     * @return
     */
    boolean setConfigValue(SnakeConfigInfoDTO snakeConfigInfo) throws Exception;

    /**
     * 项目下的配置列表
     * @param domain
     * @param startIndex
     * @param limit
     * @return
     */
    List<SnakeConfigInfoDTO> getConfigItems(String domain, int startIndex, int limit) throws Exception;

    /**
     * 获得单个配置信息
     * @param snakeConfigInfo
     * @return
     * @throws Exception
     */
    SnakeConfigInfoDTO getConfigInfo(SnakeConfigInfoDTO snakeConfigInfo) throws Exception;

    /**
     * 删除配置信息
     * @param snakeConfigInfo
     * @return
     */
    boolean deleteConfigItem(SnakeConfigInfoDTO snakeConfigInfo) throws Exception;
}
