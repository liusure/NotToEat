package com.notoeat.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by l on 2017/4/24.
 */
public class JsonResult<T> {
    private Boolean success=false;
    private T result;
    private Map params = new HashMap();
    private String msg;

    public Boolean getSuccess() {
        return success;
    }
    public void setSuccess() {
        this.success = true;
    }
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
