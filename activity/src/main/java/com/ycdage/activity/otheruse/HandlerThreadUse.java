package com.ycdage.activity.otheruse;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

public class HandlerThreadUse extends BaseHelper{

    private final Handler handler;

    public HandlerThreadUse() {
        HandlerThread handlerThread = new HandlerThread("mm"){
            @Override
            public void run() {
                sendHelperMsg("线程中执行的方法  " + Thread.currentThread().getName());
                super.run();
            }

        };
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                //todo 实际上子线程的操作都放在了这里
                sendHelperMsg(msg.obj +"  "+ Thread.currentThread().getName());
            }
        };
    }

    public void sendMsg(){
        Message message = new Message();
        message.obj = "发送消息111";
        handler.sendMessage(message);
    }


}
