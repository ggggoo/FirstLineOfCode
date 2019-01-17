package com.ycdage.activity.simple;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.ycdage.activity.base.BaseFlowActivity;

public class HandlerUseActivity extends BaseFlowActivity {

    private Handler handler1;
    @Override
    protected void addButtons() {
        createNewItemTextView("子线程1创建Handler", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createHandlerInThreads();
            }
        });

        createNewItemTextView("子线程2使用handler", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useHandlerInThreads();
            }
        });
    }

    private void useHandlerInThreads() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(handler1 != null){
                    System.out.println("这是子线程2"+Thread.currentThread().getName());
                    handler1.sendEmptyMessage(0);
                }
            }
        }).start();
    }

    private void createHandlerInThreads() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                handler1 = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        System.out.println("这是子线程1"+Thread.currentThread().getName());
                    }
                };
                Looper.loop();
            }
        }).start();
    }
}
