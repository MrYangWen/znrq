package com.cq.xm.znrq.util.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.cq.xm.znrq.interf.IBroadcast;

/**
 * Created by JackMar on 2017/6/9.
 * 邮箱：1261404794@qq.com
 */

public class BroMessage extends BroadcastReceiver {
    protected IBroadcast iBroadcast;

    public BroMessage(IBroadcast iBroadcast) {
        this.iBroadcast = iBroadcast;
    }

    public void onReceive(Context context, Intent intent) {
        String filter = intent.getStringExtra("filter");
        this.iBroadcast.onBroadcastMessage(context, intent, filter);
    }
}
