package com.ycdage.activity.otheruse;

import com.ycdage.activity.base.BaseFlowActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class OtherUserActivity extends BaseFlowActivity {

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void addButtons() {
        createNewItemTextView(ClassUseHelper.class,"使用main","main");
        createNewItemTextView(ClassUseHelper.class,".class","useDotClass");
        createNewItemTextView(ClassUseHelper.class,"class.forName","useClassForName");
        createNewItemTextView(ClassUseHelper.class,"class.getClass","useGetClass");

        HandlerThreadUse handlerThreadUse = new HandlerThreadUse();
        createNewItemTextView(handlerThreadUse,"handlerThread","sendMsg");

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
}
