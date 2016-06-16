package com.mrliu.house.utils;

import com.mrliu.house.bean.NewsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Liu on 2016/5/25.
 */
public class JsonParser {

    public static List<NewsBean> getListFromJson(String jsonString){
        List<NewsBean> list = new ArrayList<NewsBean>();

        try {
            JSONObject jo = new JSONObject(jsonString);

            JSONArray ja_data = jo.getJSONArray("data");

            for(int i = 0; i < ja_data.length(); i++){
                JSONObject jo_i = ja_data.getJSONObject(i);
                int type = jo_i.getInt("type");
                String title = jo_i.getString("title");
                String summary = jo_i.getString("summary");
                String img_url = jo_i.getString("commentcount");
                String web_Url = jo_i.getString("surl");

                NewsBean bean = new NewsBean();
                bean.setType(type);
                bean.setTitle(title);
                bean.setSummary(summary);
                bean.setImg_url(img_url);
                bean.setWeb_url(web_Url);

                list.add(bean);
            }

            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
