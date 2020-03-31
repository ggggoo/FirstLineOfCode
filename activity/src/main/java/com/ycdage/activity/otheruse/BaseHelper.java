package com.ycdage.activity.otheruse;

import org.greenrobot.eventbus.EventBus;

public class BaseHelper {

    protected static void sendHelperMsg(String msg){
        EventBus.getDefault().post(new HelperMsg(msg));
    }

    public static class HelperMsg{
        String msg;

        public HelperMsg(String msg) {
            this.msg = msg;
        }
    }
}
