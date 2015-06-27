package com.ysq.happens.transition;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.ysq.happens.database.NewsProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/27.
 */
public class NewsSearchManager {
    private Context mContext;
    public NewsSearchManager(Context context) {
        mContext = context;
    }
    public void searchNeteaseNewsList(String url, final Handler handler) {
        NewsListSearch listSearch = new NewsListSearch(url, new NewsListSearch.NewsListSearchEndListener() {
            @Override
            public void onNeasSearchEnd(Object list) {
                List<News> newsList = (List<News>)list;
                ContentValues[] values = getContentValues(newsList);
                Log.d("NewsSearchManager", values[0].toString());
                mContext.getContentResolver().bulkInsert(NewsProvider.NEWS_CONTENT_URI, values);
                handler.sendEmptyMessage(0);
            }
        });
        listSearch.search();
    }

    private ContentValues[] getContentValues(List<News> newsList) {
        List<ContentValues> valuesList = new ArrayList<ContentValues>();
        for (News news : newsList) {
            valuesList.add(news.toContentValues());
        }
        ContentValues[] arry = new ContentValues[valuesList.size()];
        return valuesList.toArray(arry);
    }
}
