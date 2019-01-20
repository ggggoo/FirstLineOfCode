package com.ycdage.activity.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.ycdage.activity.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends Activity {

    private RecyclerView mRecyclerView;
    private MRecyclerViewAdapter mAdapter;
    private List<HashMap<String, Object>> mDatas;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mDatas = new ArrayList<>();
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mAdapter = new MRecyclerViewAdapter(getBaseContext(), mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setRecyclerViewDatas() {
        mDatas.clear();
        String defaultText = "这是一串字符串";
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> data = new HashMap<>();
            Random random = new Random();
            int nextInt = random.nextInt(10);
            StringBuilder sb = new StringBuilder();
            for (int count = 0; count < nextInt; count++) {
                sb.append(defaultText);
            }
            data.put("text", sb.toString() + "");
            data.put("image", R.mipmap.icon);
            mDatas.add(data);
        }

        mAdapter.notifyDataSetChanged();
    }

    public void setHorizontal(View view) {
        mLayoutManager = new LinearLayoutManager(this, OrientationHelper.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setRecyclerViewDatas();
    }

    public void setVertical(View view) {
        mLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setRecyclerViewDatas();
    }

    public void setStaggered(View view) {
        mLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setRecyclerViewDatas();
    }

    public void toNext(View view){
        startActivity(new Intent(RecyclerViewActivity.this,IMRecycleActivity.class));
    }
}
