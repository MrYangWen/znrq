package com.cq.xm.znrq.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.cq.xm.znrq.manager.XActivityManager;
import com.cq.xm.znrq.view.customdialog.DialogMaker;
import com.cq.xm.znrq.view.statusbarutiil.StatusBarUtil;

import java.util.List;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by JackMar on 2017/3/1.
 * 邮箱：1261404794@qq.com
 */

public abstract class BaseActivity extends JActivity implements DialogMaker.DialogCallBack, EasyPermissions.PermissionCallbacks {

    protected Dialog dialog;

    private boolean isCreate = false;

    protected Context context;
    private Bundle bundle;
    public static String INTENT_KEY = "BundleKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XActivityManager.getInstance().addToActivityList(this);
        if (getLayoutId() != 0)
            setContentView(getLayoutId());
        context = this;
        setStatusBar();
        ButterKnife.bind(this);
        initView();
        isCreate = true;

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

    /**
     * 弹出对话框
     *
     * @author blue
     */
    public Dialog showAlertDialog(String title, String msg, String[] btns, boolean isCanCancelabel, final boolean isDismissAfterClickBtn, Object tag) {
        if (null == dialog || !dialog.isShowing()) {
            dialog = DialogMaker.showCommonAlertDialog(this, title, msg, btns, this, isCanCancelabel, isDismissAfterClickBtn, tag);
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
            dialog = DialogMaker.showCommenWaitDialog(this, msg, this, isCanCancelabel, tag);
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
            dialog.dismiss();
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


    @Override
    protected void onResume() {
        super.onResume();
        if (isCreate) {
            isCreate = false;
            initData();
        }
    }

    /**
     * 初始化layouy
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView();

    /**
     * 参数设置
     */
    protected abstract void initData();

    /**
     * 设置状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageView(this, 20, null);
    }

    //权限判断和申请
    protected boolean requestPermission(String[] Permissions, String des, int requestCode) {
        if (!EasyPermissions.hasPermissions(this, Permissions)) {
            EasyPermissions.requestPermissions(this, des, requestCode,
                    Permissions);
        } else {
            return true;
        }
        return false;
    }

    @Override
    protected synchronized void onDestroy() {
        XActivityManager.getInstance().removeToActivityList(this);
        super.onDestroy();
        ButterKnife.unbind(this);
        unRegsiterRe();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}