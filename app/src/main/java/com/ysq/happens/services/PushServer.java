package com.ysq.happens.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Administrator on 2015/7/22.
 */
public class PushServer extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public PushServer() {
        super("PushServer");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }
}
