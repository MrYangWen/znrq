package com.cq.xm.znrq.http.httpapi;


import com.cq.xm.znrq.bean.AddOrderEntity;
import com.cq.xm.znrq.bean.AdminRepairEntity;
import com.cq.xm.znrq.bean.AlPayEntity;
import com.cq.xm.znrq.bean.CzHsEntity;
import com.cq.xm.znrq.bean.OrderCmdEntity;
import com.cq.xm.znrq.bean.OrderEntity;
import com.cq.xm.znrq.bean.RecordEntity;
import com.cq.xm.znrq.bean.UserEntity;
import com.cq.xm.znrq.http.base.BaseResult;

import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by JackMar on 2017/2/23.
 * 邮箱：1261404794@qq.com
 */

public interface ApiUser {

    //发送验证码
    @POST("/gashub/api/BusinessUsers/GetSendCode")
    @FormUrlEncoded
    Observable<BaseResult<UserEntity>> GetSendCode(@Field("mobile") String mobile, @Field("terminal") String terminal, @Field("type") int type);

    //用户登录
    @POST("/gashub/api/mobile/login")
    @FormUrlEncoded
    Observable<BaseResult<UserEntity>> UserLogin(@Field("username") String username, @Field("password") String password);

    //手机抄表
    @POST("/gashub/api/tap/command/A003")
    @FormUrlEncoded
    Observable<BaseResult<UserEntity>> command(@Field("factoryCode") String factoryCode);

    //换算金额
    @POST("/gashub/api/mobile/charge/userpay/calculatemoney")
    @FormUrlEncoded
    Observable<BaseResult<CzHsEntity>> hsql(@Field("accountId") String accountId, @Field("quota") String quota);

    //用气纪录
    @GET("/gashub/api/mobile/charge/readremote/list")
    Observable<BaseResult<RecordEntity>> Yqjl(@Query("readRemote.meterId") String id, @Query("pageNum") int pageNum, @Query("numPerPage") String numPerPage);

    //充值纪录
    @GET("/gashub/api/mobile/charge/userpay/list")
    Observable<BaseResult<OrderEntity>> CzJl(@Query("userPay.accountId") String id, @Query("pageNum") int pageNum, @Query("numPerPage") String numPerPage);

    //充值取消
    @GET("/gashub/api/mobile/charge/userpay/cancel")
    Observable<BaseResult> deleteOrder(@Query("id") String id);

    //生成订单
    @GET("/gashub/api/mobile/charge/userpay/save")
    Observable<BaseResult<AddOrderEntity>> addOrder(@Query("userPay.accountId") String id, @Query("userPay.addAmount") String money, @Query("userPay.addNumber") String ql, @Query("userPay.payType") String type);

    //支付签名
    @GET("/gashub/api/mobile/charge/userpay/sign")
    Observable<BaseResult<AlPayEntity>> AlPay(@Query("id") String id, @Query("userPay.payType") String type);

    //手机开阀
    @GET("/gashub/api/hub/tap/command/2051")
    Observable<BaseResult> WhKf(@Query("meterId") String id);

    //手机关阀
    @GET("/gashub/api/hub/tap/command/2052")
    Observable<BaseResult> WhGf(@Query("meterId") String id);

    //远传表缴费
    @GET("/gashub/api/mobile/charge/userpay/writepaycmd")
    Observable<BaseResult> writepayCmd(@Query("id") String id);

    //写卡记录查询(蓝牙写卡)
    @GET("/gashub/api/mobile/charge/userpay/writepaycard")
    Observable<BaseResult<OrderCmdEntity>> lyCmd(@Query("id") String id, @Query("content") String content);

    //上报写卡
    @GET("/gashub/api/mobile/charge/userpay/writed")
    Observable<BaseResult> Report(@Query("id") String id, @Query("result") String result);

    //巡检记录保存
    @GET("/gashub/api/mobile/statistics/routinginspection/save")
    Observable<BaseResult<AdminRepairEntity>> checkSave(@Query("routingInspection.rouId") String userid, @Query("routingInspection.meterCode") String id, @Query("routingInspection.readLimit") String zd, @Query("routingInspection.rouName") String zrouNamed, @Query("routingInspection.rouTime") String rouTime, @Query("routingInspection.stat") String stat, @Query("routingInspection.remark") String remark);

    @Multipart
    @POST("/gashub/api/mobile/statistics/routinginspection/upload")
    Observable<BaseResult> upload(@Part MultipartBody.Part name, @Part MultipartBody.Part file);

}
