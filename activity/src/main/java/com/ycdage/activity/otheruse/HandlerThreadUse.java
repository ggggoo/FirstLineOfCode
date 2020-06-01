package com.ycdage.activity.otheruse;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.widget.Toast;

public class HandlerThreadUse extends BaseHelper{

    private final Handler handler;
    private Context mContext;
    int count = 0;
    private Toast toast;

    public HandlerThreadUse(Context context) {
        this.mContext = context;
        this.toast = Toast.makeText(mContext,"这是第"+count+"个",Toast.LENGTH_LONG);
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

    public void clickToast(){
        toast.setText("这是第"+count+"个");
        toast.show();
        count++;
    }


}
