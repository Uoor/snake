package com.lyy.snake.mgt.base.vo;

import com.lyy.snake.mgt.base.enums.AjaxResultCodeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fanshuai on 17/4/18.
 */
public class AjaxResult implements Serializable {
    public AjaxResultCodeEnum code = AjaxResultCodeEnum.SUCCESS;
    public String errorMsg;
    public String tipsMsg;
    public Map<String,Object> data = new HashMap<String, Object>();

    public void setVal(String key,Object value){
        data.put(key,value);
    }

    public void setValues(Map<String,Object> values){
        data.putAll(values);
    }

    public int getCode() {
        return code.getCode();
    }

    public void setCode(AjaxResultCodeEnum code) {
        if (code==null){
            return;
        }
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTipsMsg() {
        return tipsMsg;
    }

    public void setTipsMsg(String tipsMsg) {
        this.tipsMsg = tipsMsg;
    }

    public Map<String, Object> getData() {
        return data;
    }

}
