package com.cq.xm.znrq.http;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import com.cq.xm.znrq.R;
import com.cq.xm.znrq.http.constant.NetDefine;
import com.cq.xm.znrq.http.interceptor.ResponseInterceptor;
import com.cq.xm.znrq.http.util.FastJsonConvertFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by JackMar on 2017/2/27.
 * 邮箱：1261404794@qq.com
 */

public class NetWorkCenter {
    //请求超时时间
    private static final int DEFAULT_TIMEOUT = 10;
    private static boolean isInit;
    private static OkHttpClient.Builder builder;
    private static FastJsonConvertFactory fastJsonConvertFactory = new FastJsonConvertFactory();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

//    /**
//     * 得到Api,需要传Header的情况
//     *
//     * @param t         接口Api
//     * @param mapHeader 需要添加的Headers
//     * @param <T>
//     * @return
//     */
//    public static <T> T getApi(Class<T> t, HashMap<String, String> mapHeader) {
//        if (!isInit) {
//            initInterceptor();
//        }
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(NetDefine.HostUrl).client(genericClient(mapHeader))
//                //增加返回值为String的支持
//                .addConverterFactory(ScalarsConverterFactory.create())
//                //增加返回值为Gson的支持(以实体类返回)
//                .addConverterFactory(GsonConverterFactory.create())
//                //增加返回值为Oservable<T>的支持
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
//        return retrofit.create(t);
//    }

    /**
     * 得到APi,不需要传Header
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getApi(Class<T> t) {
        builder = new OkHttpClient.Builder();
        initInterceptor();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(NetDefine.getInstance().getUrl()).client(genericClient())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(fastJsonConvertFactory)
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(rxJavaCallAdapterFactory).build();
        return retrofit.create(t);
    }

    /**
     * @param t
     * @param HostUrl 自定义上传地址
     * @param <T>
     * @return
     */
    public synchronized static <T> T getApi(Class<T> t, String HostUrl) {
        initInterceptor();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(HostUrl).client(genericClient())
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(new FastJsonConvertFactory())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        return retrofit.create(t);
    }

    /**
     * 添加请求的Header
     * 此处既可以添加header,可以不添加Header
     * <p>
     * 如果不需要传header，直接传参数null
     *
     * @return
     */
    static OkHttpClient genericClient() {
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .build();
                return chain.proceed(request);
            }

        }).build();

        //builder.sslSocketFactory(HttpsTrustManager.getCertificates(con.getResources().openRawResource(R.raw.server)));
        HTTPSCerUtils.setTrustAllCertificate(builder);
        return builder.build();
    }

    /**
     * 初始化设置拦截器
     * 第一个
     * 这里的拦截器主要是使用OkHttp提供的HttpLoggingInterceptor
     * 在测试阶段方便打印请求的返回数据
     * <p>
     * 第二个
     * 添加返回数据的自定义拦截器，这个拦截器是为了处理返回的数据
     * 判断请求是否成功，返回数据是否正确
     */
    private static void initInterceptor() {
        if (NetDefine.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("JackMr_RxJava", message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        builder.addInterceptor(new ResponseInterceptor());
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        isInit = true;
    }
}
