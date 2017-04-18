package com.lyy.snake.mgt.dto;

import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fanshuai on 17/4/18.
 */
public class MenuDetailDTO implements Serializable{
    public String menuId;
    public String menuName;
    public String menuUrl;
    public int level;

    public MenuDetailDTO(){}
    public MenuDetailDTO(String name,String url,int level){
        this.menuName=name;
        this.menuUrl=url;
        this.level=level;
    }
    public List<MenuDetailDTO> childMenu;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public List<MenuDetailDTO> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<MenuDetailDTO> childMenu) {
        this.childMenu = childMenu;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
