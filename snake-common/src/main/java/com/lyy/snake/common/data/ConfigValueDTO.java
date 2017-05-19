package com.lyy.snake.common.data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by fanshuai on 17/5/8.
 */
public class ConfigValueDTO implements Serializable{
    private String domain;
    private String key;
    private String desc;
    private String value;
    private Date addTime ;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
