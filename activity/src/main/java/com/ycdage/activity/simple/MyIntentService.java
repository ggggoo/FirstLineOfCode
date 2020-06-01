package com.ycdage.activity.simple;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("My");
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.d("MyIntentService1",startId + " in " +
                Thread.currentThread().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("MyIntentService",intent.getStringExtra("name") + " in " +
                Thread.currentThread().getName());
    }
}
