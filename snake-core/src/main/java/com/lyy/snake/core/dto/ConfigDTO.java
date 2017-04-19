package com.lyy.snake.core.dto;

import java.io.Serializable;

/**
 * Created by fanshuai on 17/4/19.
 */
public class ConfigDTO implements Serializable{
    private String key;
    private String val;
    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
