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
            List<MenuDetailDTO> menuList = new ArrayList<MenuDetailDTO>();
            MenuDetailDTO projectMenu = new MenuDetailDTO("项目管理","",1);
            List<MenuDetailDTO> projectMenuChild = new ArrayList<MenuDetailDTO>();
            projectMenuChild.add(new MenuDetailDTO("项目组管理","/projectGroup",2));
            projectMenu.setChildMenu(projectMenuChild);

            menuList.add(projectMenu);
            result.setVal("menuList",menuList);
            result.setCode(AjaxResultCodeEnum.SUCCESS);
            result.setTipsMsg("处理成功");
        }catch (Exception e){
            result.setCode(AjaxResultCodeEnum.EXCEPTION);
            result.setErrorMsg(e.getMessage());
            result.setTipsMsg("处理失败，请重试");
        }
        return result;
    }

}
