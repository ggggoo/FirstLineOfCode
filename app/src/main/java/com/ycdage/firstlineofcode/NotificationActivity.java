package com.ycdage.firstlineofcode;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * https://blog.csdn.net/guolin_blog/article/details/79854070
 */
public class NotificationActivity extends Activity {

    private ListView notificationItems;
    private List<String> notificationItemNames = new ArrayList<String>();
    private List<String> notificationOnClick = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        setListAdapter();
    }

    private void setListAdapter() {
        initData();
        notificationItems = (ListView) findViewById(R.id.notification_listview);
        notificationItems.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
                notificationItemNames));
        notificationItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    NotificationActivity.this.getClass().getMethod(notificationOnClick.get(position)).invoke(NotificationActivity.this);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initData() {
        addData("发送高等级通知", "sendNotificationHide");
        addData("发送默认通知", "sendNotification");
        addData("发送通知", "sendNotification");
        addData("发送通知", "sendNotification");
    }

    public void sendNotificationHide() {
        Log.d(new Throwable().getStackTrace()[1].getMethodName(), "send_before1");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(new Throwable().getStackTrace()[1].getMethodName(), "send_before2");
            String notificationID = "test";
            String notificationName = "发送测试通知";
            int notificationImportance = NotificationManager.IMPORTANCE_HIGH;

            createNotificationChannel(notificationID, notificationName, notificationImportance);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String notificationID,
                                           String notificationName,
                                           int notificationImportance) {
        NotificationChannel notificationChannel = new NotificationChannel(notificationID, notificationName, notificationImportance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Objects.requireNonNull(notificationManager).createNotificationChannel(notificationChannel);
    }

    private void addData(String itemName, String method) {
        notificationItemNames.add(itemName);
        notificationOnClick.add(method);
    }

}
