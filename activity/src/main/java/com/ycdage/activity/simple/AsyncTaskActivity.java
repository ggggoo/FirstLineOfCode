package com.ycdage.activity.simple;

import android.os.AsyncTask;
import android.view.View;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.ycdage.activity.base.BaseFlowActivity;

import java.io.IOException;

public class AsyncTaskActivity extends BaseFlowActivity {

    @Override
    protected void initElement() {

    }

    @Override
    protected void addButtons() {
        createNewItemTextView("按钮1", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new MyAsyncTask().execute();
            }
        });
    }


    /**
     * onPreExecute:与创建的线程一致,ui
     * doInBackground:子线程，不可更新ui
     * onPostExecute、onProgressUpdate：ui线程，可以更新
     */
    class MyAsyncTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            showLogcat("准备开始下载图片");

        }

        @Override
        protected void onPostExecute(String s) {
            showLogcat("下载完成： " + s);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            for(String s : values){
                showLogcat(s);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            showLogcat("doInBackground"+Thread.currentThread().getName());
            publishProgress("开始下载...");
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://api.apiopen.top/getImages").build();
            try {
                publishProgress("下载url:" + request.urlString());
                Response execute = client.newCall(request).execute();
                return execute.body().string();
            } catch (IOException e) {
                publishProgress("下载失败:" + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
    }
}
