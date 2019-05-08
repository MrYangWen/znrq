package com.cq.xm.znrq.http.httphelper;


import com.cq.xm.znrq.bean.AddOrderEntity;
import com.cq.xm.znrq.bean.AdminRepairEntity;
import com.cq.xm.znrq.bean.AlPayEntity;
import com.cq.xm.znrq.bean.CzHsEntity;
import com.cq.xm.znrq.bean.OrderCmdEntity;
import com.cq.xm.znrq.bean.OrderEntity;
import com.cq.xm.znrq.bean.RecordEntity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.NetWorkCenter;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.httpapi.ApiUser;
import com.cq.xm.znrq.http.subscriber.ProgressSubscriber;
import com.cq.xm.znrq.http.util.HttpResultFunc;
import com.cq.xm.znrq.http.util.RXJavaUtil;
import com.cq.xm.znrq.util.StrUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;

/**
 * @author dapeng
 * @title 封装用户模块的接口调用
 * @data 2016/9/5.
 */
public class HttpHelperUser {
    //接口类
    private static ApiUser mApiUser;

    //私有的构造方法
    private HttpHelperUser() {
        //手动创建一个OkHttpClient并设置超时时间
        mApiUser = NetWorkCenter.getApi(ApiUser.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpHelperUser INSTANCE = new HttpHelperUser();
    }

    //获取单例
    public static HttpHelperUser getInstance() {
        return HttpHelperUser.SingletonHolder.INSTANCE;
    }

    //获取短信验证码
    public void test(Subscriber<BaseResult> subscriber) {
    }

    //获取短信验证码
    public void getSendCode(String mobile, int type, Subscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.GetSendCode(mobile, "Android", type);
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //登录
    public void userLogin(String name, String password, Subscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.UserLogin(name, password).map(new HttpResultFunc<UserEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }


    //手机抄表
    public void command(String factoryCode, Subscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.command(factoryCode).map(new HttpResultFunc<UserEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //换算金额
    public void hsql(String accountId, String quota, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.hsql(accountId, quota).map(new HttpResultFunc<CzHsEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //用气纪录
    public void Yqjl(String id, int pageNum, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.Yqjl(id, pageNum, "5").map(new HttpResultFunc<RecordEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //充值纪录
    public void CzJl(String id, int pageNum, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.CzJl(id, pageNum, "10").map(new HttpResultFunc<OrderEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //充值取消
    public void deleteOrder(String id, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.deleteOrder(id);
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //生成订单
    public void addOrder(String id, String money, String ql, String type, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.addOrder(id, money, ql, type).map(new HttpResultFunc<AddOrderEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }


    //订单支付宝app接口签名
    public void AlPay(String id, String type, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.AlPay(id, type).map(new HttpResultFunc<AlPayEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //.手机开阀
    public void WhKf(String id, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.WhKf(id);
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //手机关阀
    public void WhGf(String id, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.WhGf(id);
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //远传表缴费
    public void writepayCmd(String id, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.writepayCmd(id);
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //写卡记录查询(蓝牙写卡)
    public void lyCmd(String id, String content, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.lyCmd(id, content).map(new HttpResultFunc<OrderCmdEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }

    //上报写卡(蓝牙写卡)
    public void Report(String id, String result, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.Report(id, result);
        RXJavaUtil.toSubscribe(observable, subscriber);
    }


    //巡检记录保存
    public void checkSave(String usreid, String id, String zd, String name, String time, String type, String bz, ProgressSubscriber<BaseResult> subscriber) {
        Observable observable = mApiUser.checkSave(usreid, id, zd, name, time, type, bz).map(new HttpResultFunc<AdminRepairEntity>());
        RXJavaUtil.toSubscribe(observable, subscriber);
    }


    //巡检记录图片上传
    public void upload(String id, String filePath, ProgressSubscriber<BaseResult> subscriber) {
        File file = null;
        MultipartBody.Part body = null;
        RequestBody requestBody = null;
        Observable<BaseResult> observable = null;
        if (!StrUtil.isEmpty(filePath)) {
            file = new File(filePath);
            requestBody = RequestBody.create(MediaType.parse("img/*;text/plain;charset=utf-8"), file);
            body = MultipartBody.Part.createFormData("filedata", file.getName(), requestBody);
            MultipartBody.Part part = MultipartBody.Part.createFormData("routingInspection.id", id);
            observable = mApiUser.upload(part, body);
        }
        RXJavaUtil.toSubscribe(observable, subscriber);
    }
}
