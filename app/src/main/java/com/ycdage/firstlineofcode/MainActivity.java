package com.ycdage.firstlineofcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ycdage.firstlineofcode.ipc.BinderActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private List<String> mTitleName = new ArrayList<>();
    private List<Class<?>> mActivities = new ArrayList<>();
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTitle();
        mListView = findViewById(R.id.main_list);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,mTitleName);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(MainActivity.this,mActivities.get(i)));
            }
        });
    }

    private void addModule(String titleName, Class<?> activityName) {
        mTitleName.add(titleName);
        mActivities.add(activityName);
    }


    private void addTitle() {
        addModule("Notification",NotificationActivity.class);
        addModule("Binder",BinderActivity.class);
    }
}
