package com.cq.xm.znrq.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.cq.xm.znrq.interf.IBroadcast;
import com.cq.xm.znrq.manager.BroadcastManager;
import com.cq.xm.znrq.util.ToastUtil;

/**
 * Created by JackMar on 2017/6/9.
 * 邮箱：1261404794@qq.com
 */

public class JFragment extends Fragment implements IBroadcast {


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
     * 发送广播
     *
     * @param intent
     * @param filter
     */
    public void sendBroMessage(Intent intent, String filter) {
        BroadcastManager.getInstance().sendBroadcastMessage(this, intent, filter);
    }

    public void showToast(String Msg) {
        ToastUtil.getInstance().showToast(getActivity(), Msg);
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
        return getActivity();
    }
}
