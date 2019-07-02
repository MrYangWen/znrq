package com.cq.xm.znrq.http.util;

/**
 * Created by JackMar on 2017/2/28.
 * 邮箱：1261404794@qq.com
 */


import com.cq.xm.znrq.http.base.BaseResult;

import rx.functions.Func1;

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
 */
public class HttpResultFunc<T> implements Func1<BaseResult<T>, T> {
    @Override
    public T call(BaseResult<T> tBaseResult) {
        //判断返回数据的状态
//        if (tBaseResult.getCode() != 2000) {
//            throw new ApiException(tBaseResult.getMsg());
//        }
        return tBaseResult.getAttrs();
    }
}