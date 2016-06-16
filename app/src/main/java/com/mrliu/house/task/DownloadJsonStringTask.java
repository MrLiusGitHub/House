package com.mrliu.house.task;

import android.os.AsyncTask;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Mr.Liu on 2016/5/25.
 */
public class DownloadJsonStringTask extends AsyncTask<String, Void, String>{

    private DownloadJsonCallback downloadJsonCallback;

    public DownloadJsonStringTask(DownloadJsonCallback downloadJsonCallback) {
        this.downloadJsonCallback = downloadJsonCallback;
    }

    public interface DownloadJsonCallback
    {
        public void downloadFinish(String json);
    }

    @Override
    protected String doInBackground(String... params) {

        Request request = new Request.Builder()
                .url(params[0])
                .build();
        OkHttpClient client = new OkHttpClient();

        try {
            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        downloadJsonCallback.downloadFinish(s);
    }
}
