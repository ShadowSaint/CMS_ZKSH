package com.cdzksh.index.domain;

/**
 * @Author Created by ShadowSaint on 2018/7/5
 */
public class ResultVO {
    private boolean status = true;
    private Object data;
    private String message = "请求成功";
    private int code=0;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
