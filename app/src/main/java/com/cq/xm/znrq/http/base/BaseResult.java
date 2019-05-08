package com.cq.xm.znrq.http.base;

/**
 * 返回数据的基类
 * Created by JackMar on 2017/2/28.
 * 邮箱：1261404794@qq.com
 */

public class BaseResult<T> {
    /**
     * statusCode : 300
     * message : 登录失败,用户不存在
     * navTabId :
     * forwardUrl :
     * rel :
     * callbackType : closeCurrent
     * attrs : {}
     */

    private int statusCode;
    private String message;
    private String navTabId;
    private String forwardUrl;
    private String rel;
    private String callbackType;
    private T attrs;

    public T getAttrs() {
        return attrs;
    }

    public void setAttrs(T attrs) {
        this.attrs = attrs;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", navTabId='" + navTabId + '\'' +
                ", forwardUrl='" + forwardUrl + '\'' +
                ", rel='" + rel + '\'' +
                ", callbackType='" + callbackType + '\'' +
                '}';
    }
}
