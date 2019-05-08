package com.cq.xm.znrq.http.subscriber;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.cq.xm.znrq.http.exception.retrofitexception.ApiException;
import com.cq.xm.znrq.util.NetWorkUtil;
import com.cq.xm.znrq.util.ToastUtil;
import com.cq.xm.znrq.view.customdialog.DialogMaker;
import com.cq.xm.znrq.view.customdialog.ProgressCircleDialogHandler;
import com.cq.xm.znrq.view.customdialog.ProgressDialogHandler;
import com.cq.xm.znrq.view.rvlist.RefreshUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * @Title
 * @Author JackMar
 * @Date 2017-03-10 17:14
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements DialogMaker.DialogCallBack {

    private IOnNextListener mIOnNextListener;
    private ProgressCircleDialogHandler mProgressDialogHandler;
    private boolean needCircle = true;
    private Context context;
    private RefreshUtil refreshUtil;

    public ProgressSubscriber(Context context, boolean needCircle, IOnNextListener mIOnNextListener) {
        this.mIOnNextListener = mIOnNextListener;
        this.context = context;
        this.needCircle = needCircle;
        mProgressDialogHandler = new ProgressCircleDialogHandler(context, this, true);
    }

    public ProgressSubscriber(Context context, boolean needCircle, RefreshUtil refreshUtil, IOnNextListener mIOnNextListener) {
        this.mIOnNextListener = mIOnNextListener;
        this.context = context;
        this.needCircle = needCircle;
        this.refreshUtil = refreshUtil;
        mProgressDialogHandler = new ProgressCircleDialogHandler(context, this, true);
    }

    public ProgressSubscriber(Context context, IOnNextListener mIOnNextListener) {
        this.mIOnNextListener = mIOnNextListener;
        this.context = context;
        mProgressDialogHandler = new ProgressCircleDialogHandler(context, this, true);
    }

    public ProgressSubscriber(Context context, RefreshUtil refreshUtil, IOnNextListener mIOnNextListener) {
        this.mIOnNextListener = mIOnNextListener;
        this.context = context;
        this.refreshUtil = refreshUtil;
        mProgressDialogHandler = new ProgressCircleDialogHandler(context, this, true);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null && needCircle) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null && needCircle) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
        if (refreshUtil != null) {
            refreshUtil.getmRecyclerView().showEmptyView();
            refreshUtil.completeRefreshAndLoad();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if (NetWorkUtil.isNetworkConnected(context)) {
            showProgressDialog();
        } else {
//            if (refreshUtil != null && refreshUtil.getErrorView() != null) {
//                refreshUtil.getErrorView().setmTvText("啦啦啦啦");
//            }
            ToastUtil.getInstance().showToast(context, "请检查您的网络连接");
            onCompleted();
            return;
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        // Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.i("onError", e.getMessage());
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ApiException) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "error2:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mIOnNextListener != null) {
            mIOnNextListener.onNext(t);
        }
        dismissProgressDialog();
    }

//    /**
//     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
//     */
//    @Override
//    public void onCancelProgress() {
//        if (!this.isUnsubscribed()) {
//            this.unsubscribe();
//        }
//    }

    @Override
    public void onButtonClicked(Dialog dialog, int position, Object tag) {

    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelDialog(Dialog dialog, Object tag) {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}