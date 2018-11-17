package com.ycdage.firstlineofcode;

import android.app.Application;

import com.landicorp.android.unionpay.log.LogUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new LogUtils.Builder()
                .withFolderPath("First")
                .withFormatterPattern(new LogUtils.FormatterPattern().withTime().withThread().withTAG().withRelative().withLevel().withFileAndLines())
                .withMaxPreservedDays(3)
                .build().init(this, true);
    }
}
