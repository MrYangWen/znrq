package com.cq.xm.znrq.http.interceptor;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.cq.xm.znrq.http.base.BaseResult;
import com.cq.xm.znrq.http.exception.retrofitexception.ApiException;
import com.cq.xm.znrq.http.exception.retrofitexception.NetworkException;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @Title 拦截器
 * @Date 2016-05-23 17:14
 */

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a {@linkplain
 * OkHttpClient#networkInterceptors() network interceptor}. <p> The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */
public final class ResponseInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            throw e;
        }
        //判断请求是否成功
        if (response.code() == 200) {
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            String baseResult = buffer.clone().readString(UTF8);
            BaseResult result = null;
            //判断是否有返回数据
            if (!TextUtils.isEmpty(baseResult)) {
                try {
                    //获取返回的实体（最外层）
                    result = JSON.parseObject(baseResult, BaseResult.class);
//                    ToastUtil.getInstance().showToast(XApplication.getInstance(), result.getMsg());
                } catch (Exception e) {
                }
                if (result != null) {
                    //此处处理服务器返回的判断
                    int state = result.getStatusCode();
                    if (state != 200) {
                        throw new ApiException(result.getMessage());
                    }
                }
            } else {
                //将服务器的异常抛出处理
                throw new NetworkException(response.code());
            }
        } else {
            throw new NetworkException(response.code());
        }
        return response;
    }
}
