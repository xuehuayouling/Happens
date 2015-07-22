package com.ysq.happens.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2015/7/22.
 */
public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent pushSever = new Intent(context, PushServer.class);
        context.startService(pushSever);
    }
}
