package com.cq.xm.znrq.bean;


/**
 * Created by jiang on 2016/11/11.
 */

public class PayWxBean {

    /**
     * appId : wxd94c48e1dcc072c5
     * mchId : 1490024122
     * nonceStr : z1AVdcaR606cuLkUx2IO5vZTViGYER5K
     * package : prepay_id=wx201710141711192906c6ed9e0849199965
     * prepayId : wx201710141711192906c6ed9e0849199965
     * sign : E596BE656AEBB92F7FB92110775FF5BF
     * signType : MD5
     * timeStamp : 1507972266963
     */

    private String appId;
    private String partnerid;
    private String nonceStr;
    private String prepayId;
    private String sign;
    private String signType;
    private String timeStamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
