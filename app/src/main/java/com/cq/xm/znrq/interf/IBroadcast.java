package com.cq.xm.znrq.interf;

import android.content.Context;
import android.content.Intent;

/**
 * Created by JackMar on 2017/6/9.
 * 邮箱：1261404794@qq.com
 */

public interface IBroadcast {
    void openBroadcastReceiver();

    void sendBroadcastMessage(String var1);

    void sendBroadcastMessage(Intent var1, String var2);

    void onBroadcastMessage(Context context, Intent intent,String filter);

    Context getContext();
}
