package com.lyy.snake.mgt.action;

import com.alibaba.fastjson.JSON;
import com.lyy.snake.core.client.ConfigManager;
import com.lyy.snake.core.dto.ConfigDTO;
import com.lyy.snake.mgt.base.enums.AjaxResultCodeEnum;
import com.lyy.snake.mgt.base.vo.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/4/18.
 */
@Controller
@RequestMapping(value = "/snake/config")
public class ConfigAction {


    @RequestMapping(value = "/value/key")
    @ResponseBody
    public AjaxResult getConfigByKey(String key){
        AjaxResult result = new AjaxResult();
        try {
            String value = ConfigManager.getDefaultInstance().getConfigValue(key);
            result.setVal("configValue", value);
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
            Map<String,String> configValueMap = ConfigManager.getDefaultInstance().getDomainConfigValues(domain);
            List<ConfigDTO> configList = new ArrayList<ConfigDTO>();
            for (Map.Entry<String,String> entry:configValueMap.entrySet()){
                ConfigDTO config = new ConfigDTO();
                config.setKey(entry.getKey());
                config.setVal(entry.getValue());
                configList.add(config);
            }
            result.setVal("configList", configList);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }

    @RequestMapping(value = "/value/update",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateConfigValue(String key,String value){
        AjaxResult result = new AjaxResult();
        try {
            boolean updateSuccess = ConfigManager.getDefaultInstance().setConfigValues(key, value);
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
            boolean updateSuccess = ConfigManager.getDefaultInstance().deleteConfig(key);
            result.setVal("updateSuccess", updateSuccess);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }
}
