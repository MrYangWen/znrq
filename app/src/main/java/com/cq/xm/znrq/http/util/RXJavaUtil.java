package com.cq.xm.znrq.http.util;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by JackMar on 2017/2/28.
 * 邮箱：1261404794@qq.com
 */
public class RXJavaUtil {
    /**
     * 线程调度，将网络请求放在后台线程操作，在主线程上界面显示
     *
     * @param o
     * @param s
     * @param <T>
     */
    public static <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
                .subscribe(s);
    }
}
