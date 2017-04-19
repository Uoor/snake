package com.lyy.snake.mgt.action;

import com.lyy.snake.core.client.ConfigManager;
import com.lyy.snake.core.dto.ConfigDTO;
import com.lyy.snake.mgt.base.enums.AjaxResultCodeEnum;
import com.lyy.snake.mgt.base.vo.AjaxResult;
import com.lyy.snake.mgt.dto.MenuDetailDTO;
import com.lyy.snake.mgt.dto.ProjectDetailDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fanshuai on 17/4/19.
 */
@Controller
public class ProjectAction {

    @RequestMapping(value = "/snake/projectList")
    @ResponseBody
    public AjaxResult projectList(){
        AjaxResult result = new AjaxResult();
        try {
            List<ProjectDetailDTO> projectList = new ArrayList<ProjectDetailDTO>();
            projectList.add(new ProjectDetailDTO("默认", "demo", "配置"));
            result.setVal("projectList", projectList);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }

    @RequestMapping(value = "/allproject")
    @ResponseBody
    public AjaxResult allProject(){
        AjaxResult result = new AjaxResult();
        try {
            List<String> allDomains = ConfigManager.getDefaultInstance().getAllDomains();
            result.setVal("projects", allDomains);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }
}
