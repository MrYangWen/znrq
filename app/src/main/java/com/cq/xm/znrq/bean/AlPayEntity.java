package com.cq.xm.znrq.bean;

/**
 * @author dapeng
 * @title
 * @data 2017/9/10
 * @email 919142784@qq.com
 */
public class AlPayEntity {
    /**
     * payInfo : partner="2088721764589733"&seller_id="cqzxkj2012@163.com"&out_trade_no="17091100000003"&subject="燃气缴费"&body="燃气表243434[21]缴费"&total_fee="0.01"&notify_url="http://222.180.163.205:8080/gashub/api/alipay/notify"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="JLmPSotyqJWheEBQg0orjY%2F2LquH0neSyC%2F%2BTzi0Vz4vORaXjl8W9Zpb4wXm0TvGnTeGg3zJXyWOAc3QddS1gQ49BfiU8MBJCvn0GPjqln22lpBhPTrvUEhig3xY1reZhtkPWdIo9cBKUT%2Fp5hF8Gy8xBmewlmr11oxCcbh77JU%3D"&sign_type="RSA"
     */

    private String payInfo;

    public String getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(String payInfo) {
        this.payInfo = payInfo;
    }
}
