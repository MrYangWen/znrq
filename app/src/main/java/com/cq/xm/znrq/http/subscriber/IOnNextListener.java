package com.cq.xm.znrq.http.subscriber;

/**
 * @Title
 * @Author luojiang
 * @Date 2016-05-23 17:14
 */
public interface IOnNextListener<T> {
    void onNext(T t);
}
