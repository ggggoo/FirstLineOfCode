package com.ycdage.activity.simple;

import android.os.AsyncTask;
import android.view.View;

import com.ycdage.activity.base.BaseFlowActivity;

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


    class MyAsyncTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
            System.out.println("onPreExecute"+Thread.currentThread().getName());
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println("onPostExecute"+Thread.currentThread().getName());
        }

        @Override
        protected void onProgressUpdate(String... values) {
            System.out.println("onProgressUpdate"+Thread.currentThread().getName());
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            System.out.println("onCancelled"+Thread.currentThread().getName());
        }

        @Override
        protected String doInBackground(String... strings) {
            System.out.println("doInBackground"+Thread.currentThread().getName());
            return null;
        }
    }
}
