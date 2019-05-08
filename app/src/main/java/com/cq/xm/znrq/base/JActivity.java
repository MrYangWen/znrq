package com.cq.xm.znrq.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.cq.xm.znrq.interf.IBroadcast;
import com.cq.xm.znrq.manager.BroadcastManager;
import com.cq.xm.znrq.util.ToastUtil;

/**
 * Created by JackMar on 2017/6/9.
 * 邮箱：1261404794@qq.com
 */

public class JActivity extends FragmentActivity implements IBroadcast {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 注册广播接收器
     */
    public void regsiterRe() {
        BroadcastManager.getInstance().registerReceiver(this);
    }

    /**
     * 注销广播接收器
     */
    public void unRegsiterRe() {
        BroadcastManager.getInstance().unregisterReceiver(this);
    }

    /**
     * 设置editText的光标到最后
     *
     * @param editText
     */
    public void setEditTextCursor(EditText editText) {
        editText.setSelection(editText.getText().length());
    }

    /**
     * 发送广播
     *
     * @param intent
     * @param filter
     */
    public void sendBroMessage(Intent intent, String filter) {
        BroadcastManager.getInstance().sendBroadcastMessage(this, intent, filter);
    }

    public void showToast(String Msg) {
        ToastUtil.getInstance().showToast(this, Msg);
    }


    @Override
    public void openBroadcastReceiver() {

    }

    @Override
    public void sendBroadcastMessage(String var1) {

    }

    @Override
    public void sendBroadcastMessage(Intent var1, String var2) {

    }

    @Override
    public void onBroadcastMessage(Context context, Intent intent, String filter) {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
