package com.cq.xm.znrq.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.cq.xm.znrq.view.customdialog.DialogMaker;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * @Title fragment 基类
 * @Author JackMar
 * @Date 2016-05-23 17:14
 */
public abstract class BaseFragment extends JFragment implements DialogMaker.DialogCallBack {
    public Activity context;
    protected Dialog dialog;

    protected View rootView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        regsiterRe();
    }

    /**
     * 启动页面方法
     *
     * @param tClass 目标页面
     * @param bundle 参数
     */
    public void launch(Class tClass, Bundle bundle) {
        Intent intent = new Intent(context, tClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 启动页面方法
     *
     * @param tClass 目标页面
     */
    public void launch(Class tClass) {
        Intent intent = new Intent(context, tClass);
        startActivity(intent);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
        initData();
    }


    /**
     * 参数设置
     */
    protected abstract void initView();

    /**
     * 参数设置
     */
    protected abstract void initData();


    /**
     * 初始化layouy
     *
     * @return
     */
    protected abstract int getLayoutId();


    /**
     * 弹出对话框
     *
     * @author blue
     */
    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(context, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag);
        }
        return dialog;
    }

    /**
     * 等待对话框
     *
     * @author blue
     */
    public Dialog showWaitDialog(String msg, boolean isCanCancelabel, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommenWaitDialog(context, msg, this, isCanCancelabel, tag);
        }
        return dialog;
    }

    /**
     * 关闭对话框
     *
     * @author blue
     */
    public void dismissDialog() {
        if (null != dialog && dialog.isShowing()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            }, 2000);

        }
    }

    /**
     * 点击dialog
     *
     * @param dialog
     * @param position 点击Button的索引.
     * @param tag
     */
    @Override
    public void onButtonClicked(Dialog dialog, int position, Object tag) {
    }

    /**
     * 取消dialog
     *
     * @param dialog
     * @param tag
     */
    @Override
    public void onCancelDialog(Dialog dialog, Object tag) {
    }

    //权限判断和申请
    protected boolean requestPermission(String[] Permissions, String des, int requestCode) {
        if (!EasyPermissions.hasPermissions(getActivity(), Permissions)) {
            EasyPermissions.requestPermissions(this, des, requestCode,
                    Permissions);
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(rootView);
        unRegsiterRe();
    }
}
