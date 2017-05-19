package com.lyy.snake.mgt.base.enums;

/**
 * Created by fanshuai on 17/4/18.
 */
public enum  AjaxResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(400,"失败"),
    EXCEPTION(500,"异常"),
    NOTFOUND(404,"资源不存在"),
    ;

    private AjaxResultCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
