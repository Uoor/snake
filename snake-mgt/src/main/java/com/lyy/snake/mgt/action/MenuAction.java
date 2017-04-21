package com.lyy.snake.mgt.action;

import com.lyy.snake.mgt.base.enums.AjaxResultCodeEnum;
import com.lyy.snake.mgt.base.vo.AjaxResult;
import com.lyy.snake.mgt.dto.MenuDetailDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanshuai on 17/4/18.
 */
@Controller
public class MenuAction {
    @RequestMapping(value = "/menu/list")
    @ResponseBody
    public AjaxResult getMenuList(){
        AjaxResult result = new AjaxResult();
        try {
            MenuDetailDTO projectMenu = new MenuDetailDTO("项目管理","",1);
            projectMenu.addChildMenu(new MenuDetailDTO("项目组管理", "/project/projectList.htm", 2));

            MenuDetailDTO configMenu = new MenuDetailDTO("配置中心","",1);
            configMenu.addChildMenu(new MenuDetailDTO("配置管理", "/project/projectList.htm", 2));

            List<MenuDetailDTO> menuList = new ArrayList<MenuDetailDTO>();
            menuList.add(projectMenu);
            menuList.add(configMenu);
            result.setVal("menuList",menuList);
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }

}
