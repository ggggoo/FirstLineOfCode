package com.ycdage.activity.otheruse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.ycdage.activity.base.BaseFlowActivity;
import com.ycdage.activity.screen.ScreenUtilActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OtherUserActivity extends BaseFlowActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void addButtons() {
        createNewItemTextView(ClassUseHelper.class,"使用main","main");
        createNewItemTextView(ClassUseHelper.class,".class","useDotClass");
        createNewItemTextView(ClassUseHelper.class,"class.forName","useClassForName");
        createNewItemTextView(ClassUseHelper.class,"class.getClass","useGetClass");

        HandlerThreadUse handlerThreadUse = new HandlerThreadUse(getBaseContext());
        createNewItemTextView(handlerThreadUse,"handlerThread","sendMsg");
        createNewItemTextView(handlerThreadUse,"toast","clickToast");

        createNewItemTextView("延迟跳转", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(OtherUserActivity.this,ScreenUtilActivity.class));
                    }
                },1000);
            }
        });

        createNewItemTextView("延迟跳转2", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(OtherUserActivity.this,ScreenUtilActivity.class));
                    }
                },8000);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRecvHelperMsg(BaseHelper.HelperMsg msg){
          showLogcat(msg.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
