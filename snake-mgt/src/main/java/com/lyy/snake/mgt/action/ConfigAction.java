package com.lyy.snake.mgt.action;

import com.lyy.snake.mgt.base.enums.AjaxResultCodeEnum;
import com.lyy.snake.mgt.base.vo.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setTipsMsg("处理成功");
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }
}
