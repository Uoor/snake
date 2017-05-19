package com.lyy.snake.mgt.action;

import com.lyy.snake.common.dto.SnakeConfigInfoDTO;
import com.lyy.snake.common.utils.PatternUtil;
import com.lyy.snake.manager.SnakeMamagerFactory;
import com.lyy.snake.mgt.base.enums.AjaxResultCodeEnum;
import com.lyy.snake.mgt.base.vo.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fanshuai on 17/4/18.
 */
@Controller
@RequestMapping(value = "/snake/config")
public class ConfigAction {
    String domainPattern = "^[a-zA-Z]{1}[a-zA-Z0-9\\-]*$";
    String configNamePattern = "^[a-zA-Z0-9]{1}[a-zA-Z0-9\\.\\-]*$";
    String fullKeyPattern ="^[a-zA-Z]{1}[a-zA-Z0-9\\-]*\\.[a-zA-Z0-9]{1}[a-zA-Z0-9\\.\\-]*$";
    @RequestMapping(value = "/value/key")
    @ResponseBody
    public AjaxResult getConfigByKey(String key){
        AjaxResult result = new AjaxResult();
        try {
            if (PatternUtil.isNotMatchs(key, fullKeyPattern)){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            int firstPointIndex = key.indexOf('.');
            if (firstPointIndex==-1){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            if (firstPointIndex==key.length()-1){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            String domain = key.substring(0, firstPointIndex);
            String configName = key.substring(firstPointIndex+1,key.length());
            SnakeConfigInfoDTO snakeConfigInfoDTO = new SnakeConfigInfoDTO();
            snakeConfigInfoDTO.setDomain(domain);
            snakeConfigInfoDTO.setConfigName(configName);
            SnakeConfigInfoDTO valueDto = SnakeMamagerFactory.getDefaultSnakeManager().getConfigInfo(snakeConfigInfoDTO);
            result.setVal("configValue", valueDto.getConfigValue());
            result.setVal("version",valueDto.getVersion());
            result.setVal("configDesc",valueDto.getConfigDesc());
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }

    @RequestMapping(value = "/project/values")
    @ResponseBody
    public AjaxResult getAllProjectConfig(String domain){
        AjaxResult result = new AjaxResult();
        try {
            if (PatternUtil.isNotMatchs(domain, domainPattern)){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,domain+"不符合规范");
            }
            List<SnakeConfigInfoDTO> snakeConfigInfoDTOList = SnakeMamagerFactory.getDefaultSnakeManager().getConfigItems(domain,0,1000);
            result.setVal("configList", snakeConfigInfoDTOList);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }

    @RequestMapping(value = "/value/update",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateConfigValue(String key,String value,String desc,Integer version){
        AjaxResult result = new AjaxResult();
        try {
            if (PatternUtil.isNotMatchs(key, fullKeyPattern)){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            int firstPointIndex = key.indexOf('.');
            if (firstPointIndex==-1){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            if (firstPointIndex==key.length()-1){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            String domain = key.substring(0, firstPointIndex);
            String configName = key.substring(firstPointIndex+1,key.length());
            SnakeConfigInfoDTO snakeConfigInfoDTO = new SnakeConfigInfoDTO();
            snakeConfigInfoDTO.setDomain(domain);
            snakeConfigInfoDTO.setConfigName(configName);
            snakeConfigInfoDTO.setConfigValue(value);
            snakeConfigInfoDTO.setConfigDesc(desc);
            snakeConfigInfoDTO.setVersion(version==null?0:version);
            boolean updateSuccess = SnakeMamagerFactory.getDefaultSnakeManager().setConfigValue(snakeConfigInfoDTO);
            result.setVal("updateSuccess", updateSuccess);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult deleteConfig(String key){
        AjaxResult result = new AjaxResult();
        try {
            if (PatternUtil.isNotMatchs(key, fullKeyPattern)){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            int firstPointIndex = key.indexOf('.');
            if (firstPointIndex==-1){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            if (firstPointIndex==key.length()-1){
                return AjaxResult.getFailResult(AjaxResultCodeEnum.FAIL,key+"不符合规范");
            }
            String domain = key.substring(0, firstPointIndex);
            String configName = key.substring(firstPointIndex+1,key.length());
            SnakeConfigInfoDTO snakeConfigInfoDTO = new SnakeConfigInfoDTO();
            snakeConfigInfoDTO.setDomain(domain);
            snakeConfigInfoDTO.setConfigName(configName);
            boolean updateSuccess = SnakeMamagerFactory.getDefaultSnakeManager().deleteConfigItem(snakeConfigInfoDTO);
            result.setVal("updateSuccess", updateSuccess);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }
}
