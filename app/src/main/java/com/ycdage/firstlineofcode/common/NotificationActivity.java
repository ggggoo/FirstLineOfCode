package com.ycdage.firstlineofcode.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ycdage.firstlineofcode.MainActivity;
import com.ycdage.firstlineofcode.R;

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
    private NotificationManager notificationManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        setListAdapter();
    }

    private void initData() {
        addData("发送高等级通知", "sendNotificationHide");
        addData("发送默认通知", "sendNotificationDefault");
        addData("发送响铃通知", "sendNotificationSound");
        addData("发送进度条通知", "sendNotificationProgress");
    }

    public void sendNotificationProgress(){
        Notification build = new NotificationCompat.Builder(this, "test")
                .setContentTitle("收到进度条通知")
                .setContentText("开始进度")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.icon)
                .setProgress(100,50,true)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))
                .build();
        notificationManager.notify(1, build);
    }

    public void sendNotificationHide() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = notificationManager.getNotificationChannel("test");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }

        Notification build = new NotificationCompat.Builder(this, "test")
                .setContentTitle("收到高等级通知")
                .setContentText("彩票中了一等奖！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))
                .setAutoCancel(true)
                .setTicker("测试通知来啦")
                .setNumber(2)
                .build();
        notificationManager.notify(1, build);
    }

    public void sendNotificationDefault() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = notificationManager.getNotificationChannel("test2");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                startActivity(intent);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }

        Notification build = new NotificationCompat.Builder(this, "test2")
                .setContentTitle("收到普通通知")
                .setContentText("没什么事就关掉吧")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.icon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon))
                .setAutoCancel(true)
                .build();
        notificationManager.notify(2, build);
    }

    public void sendNotificationSound() {
        //FLAG_ONE_SHOT   表示返回的PendingIntent仅能执行一次，执行完后自动取消
        //FLAG_NO_CREATE     表示如果描述的PendingIntent不存在，并不创建相应的PendingIntent，而是返回NULL
        //FLAG_CANCEL_CURRENT      表示相应的PendingIntent已经存在，则取消前者，然后创建新的PendingIntent，
        //                       这个有利于数据保持为最新的，可以用于即时通信的通信场景
        //FLAG_UPDATE_CURRENT     表示更新的PendingIntent
        PendingIntent pi = PendingIntent.getActivity(
                this,
                100,
                new Intent(NotificationActivity.this, MainActivity.class),
                PendingIntent.FLAG_CANCEL_CURRENT
        );
        Notification notification = new NotificationCompat.Builder(this, "test")
                .setContentTitle("高级通知")
                .setContentText("收到一条语音通知")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.icon)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setVibrate(new long[]{0, 300, 500, 700})
                .setTicker("从新华社发回的消息")
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                //android5.0加入了一种新的模式Notification的显示等级，共有三种：
                //VISIBILITY_PUBLIC  只有在没有锁屏时会显示通知
                //VISIBILITY_PRIVATE 任何情况都会显示通知
                //VISIBILITY_SECRET  在安全锁和没有锁屏的情况下显示通知
                .setContentIntent(pi)
                .build();
        notificationManager.notify(3, notification);
    }

    public void addNotificationChannel() {
        Log.d(new Throwable().getStackTrace()[0].getMethodName(), "send_before1");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String notificationID = "test";
            String notificationName = "发送测试通知";
            int notificationImportance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(notificationID, notificationName, notificationImportance);

            String notificationID2 = "test2";
            String notificationName2 = "发送测试通知2";
            int notificationImportance2 = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(notificationID2, notificationName2, notificationImportance2);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String notificationID,
                                           String notificationName,
                                           int notificationImportance) {
        NotificationChannel notificationChannel = new NotificationChannel(notificationID,
                notificationName, notificationImportance);
        notificationChannel.setShowBadge(true);
        Objects.requireNonNull(notificationManager).createNotificationChannel(notificationChannel);
    }

    private void addData(String itemName, String method) {
        notificationItemNames.add(itemName);
        notificationOnClick.add(method);
    }

    private void setListAdapter() {
        initData();
        addNotificationChannel();
        notificationItems = (ListView) findViewById(R.id.notification_listview);
        notificationItems.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1,
                notificationItemNames));
        notificationItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    NotificationActivity.this.getClass().getMethod(notificationOnClick.get(position))
                            .invoke(NotificationActivity.this);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
