package com.mrliu.house;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mrliu.house.activity.WebActivity;
import com.mrliu.house.adapter.NewsAdapter;
import com.mrliu.house.bean.NewsBean;
import com.mrliu.house.task.DownloadJsonStringTask;
import com.mrliu.house.utils.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://ikft.house.qq.com/index.php?" +
            "devua=appkft_480_800_GenymotionGoogleNexusS-4.1.1-API16-480x800*--*1_2.6.1_Android16&" +
            "devid=1464154049234458657&mod=appkft&buttonmore=0&cityid=3&guid=1464154049234458657&request_id" +
            "=eXEpKc02MwaX&appname=QQHouse&huid=H2516052523b&reqnum=20&" +
            "pageflag=0&majorversion=v2&act=newslist&channel=261";

    private RecyclerView rv_news;

    private String jsonString = null;

    private List<NewsBean> data = new ArrayList<NewsBean>();
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();


    }

    private void initData() {

        new DownloadJsonStringTask(new DownloadJsonStringTask.DownloadJsonCallback() {
            @Override
            public void downloadFinish(String json) {
                data.addAll(JsonParser.getListFromJson(json));
                adapter.notifyDataSetChanged();
            }
        }).execute(URL);

    }

    private void initView() {

        rv_news = ((RecyclerView) findViewById(R.id.rv_news));

        rv_news.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NewsAdapter(this, data, new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra("url", data.get(position).getWeb_url());
                startActivity(intent);
            }
        });

        rv_news.setAdapter(adapter);
    }
}
