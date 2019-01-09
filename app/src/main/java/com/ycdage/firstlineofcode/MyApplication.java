package com.ycdage.firstlineofcode;

import android.app.Application;

import com.landicorp.android.unionpay.log.Logly;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logly.Config.getInstance().setFolderPath("first")
                 //设置保存log 文件夹的名称，默认是应用名称
                .setFormatterPattern(new Logly.FormatterPattern().addTime())
                 //设置上方的log 打印格式
                .setMaxPreservedDays(3)
                //设置按天保存的日志最大保存天数，默认7 天
                .apply(this);//使设置生效
    }
}
