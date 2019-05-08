package com.cq.xm.znrq.alipay;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.cq.xm.znrq.bean.PayWxBean;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;

import java.util.Map;

/**
 * 作者：{大鹏} on 2016/11/3 10:34
 * 邮箱：919142784@qq.com
 */
public class PayHelper {
    /**
     * -------支付宝支付-----
     *
     * @param orderInfo 支付寶支付
     * @param activity
     * @param handler   支付回调通知
     */
    public static void aliPay(final String orderInfo, final Context activity, final Handler handler) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask((Activity) activity);
                String result = alipay.pay(orderInfo, true);
                Message msg = new Message();
                msg.obj = result;
                handler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * -------微信支付-----
     *
     * @param activity
     * @param bean     訂單信息
     * @throws JSONException
     */
    public static void wechatPay(Activity activity, PayWxBean bean) throws JSONException {
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(activity, null);
        // 将该app注册到微信
        msgApi.registerApp(bean.getAppId());
        IWXAPI api = WXAPIFactory.createWXAPI(activity, bean.getAppId());
        boolean isPaySupported = api.getWXAppSupportAPI() >= com.tencent.mm.sdk.constants.Build.PAY_SUPPORTED_SDK_INT;
        if (isPaySupported) {
            PayReq req = new PayReq();
            req.appId = bean.getAppId();
            req.partnerId = bean.getPartnerid();
            req.prepayId = bean.getPrepayId();
            req.nonceStr = bean.getNonceStr();
            req.timeStamp = bean.getTimeStamp();
            req.packageValue = "Sign=WXPay";
            req.sign = bean.getSign();
            req.extData = "";
            api.sendReq(req);
        } else {
            Toast.makeText(activity, "请下载最新版微信", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 支付宝授权
     *
     * @param activity
     * @param authContent
     * @param handler
     */
    public static void payAuth(final Activity activity, final String authContent, final Handler handler) {
        if (authContent == null) {
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                AuthTask authTask = new AuthTask(activity);
                final Map<String, String> result = authTask.authV2(authContent, true);
                Message msg = new Message();
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }).start();
    }
}
