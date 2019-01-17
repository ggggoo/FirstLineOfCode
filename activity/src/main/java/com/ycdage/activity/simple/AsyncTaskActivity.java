package com.ycdage.activity.simple;

import android.os.AsyncTask;

import com.ycdage.activity.base.BaseFlowActivity;

public class AsyncTaskActivity extends BaseFlowActivity {

    @Override
    protected void initElement() {

    }

    @Override
    protected void addButtons() {

    }


    private class MyAsyncTask extends AsyncTask<String,String,String>{

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String s) {
        }

        @Override
        protected void onProgressUpdate(String... values) {
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
