package com.ycdage.firstlineofcode;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ycdage.activity.fingerkey.FignerActivity;
import com.ycdage.activity.otheruse.OtherUserActivity;
import com.ycdage.activity.recyclerview.RecyclerViewActivity;
import com.ycdage.activity.screen.ScreenUtilActivity;
import com.ycdage.activity.simple.AsyncTaskActivity;
import com.ycdage.activity.simple.HandlerUseActivity;
import com.ycdage.activity.simple.ImplicitIntentActivity;
import com.ycdage.activity.simple.NotificationActivity;
import com.ycdage.firstlineofcode.ipc.BinderActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private List<String> mTitleName = new ArrayList<>();
    private List<Class<?>> mActivities = new ArrayList<>();
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = getIntent().getData();
        initThirdCallData(uri);
        addTitle();
        ListView mListView = findViewById(R.id.main_list);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_expandable_list_item_1,
                        mTitleName);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("111",System.currentTimeMillis()+"");
                startActivity(new Intent(MainActivity.this, mActivities.get(i)));
            }
        });
    }

    private void initThirdCallData(Uri uri) {
        if (isRecognizable(uri)) {
            String token = getToken(uri, "token");
            switch (token) {
                case "A1":
                    Toast.makeText(getBaseContext(), getToken(uri, "id"), Toast.LENGTH_LONG).show();
                    break;
                case "A2":
                    Toast.makeText(getBaseContext(), getToken(uri, "id"), Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(getBaseContext(), "Default", Toast.LENGTH_LONG).show();
            }
        }
    }

    private static String getToken(Uri uri, String queryName) {
        return uri.getQueryParameter(queryName);
    }

    private boolean isRecognizable(Uri uri) {
        if (uri != null) {
            String url = uri.toString();
            return url.startsWith("yang://dage:2333/mainActivity?token=");
        } else {
            return false;
        }
    }

    private void addModule(String titleName, Class<?> activityName) {
        mTitleName.add(count + "、" + titleName);
        mActivities.add(activityName);
        count++;
    }


    private void addTitle() {
        addModule("Notification", NotificationActivity.class);
        addModule("Binder", BinderActivity.class);
        addModule("屏幕适配", ScreenUtilActivity.class);
        addModule("指纹识别", FignerActivity.class);
        addModule("隐式Intent用法", ImplicitIntentActivity.class);
        addModule("RecyclerView", RecyclerViewActivity.class);
        addModule("HandlerUse", HandlerUseActivity.class);
        addModule("AsyncTask", AsyncTaskActivity.class);
        addModule("杂七杂八的东西", OtherUserActivity.class);
    }
}
