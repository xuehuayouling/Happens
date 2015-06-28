package com.ysq.happens.ui;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.widget.TextView;

import com.ysq.happens.R;
import com.ysq.happens.database.NewsProvider;
import com.ysq.happens.transition.NewsSearchManager;

/**
 * Created by Administrator on 2015/6/28.
 */
public class NewsDetailsActivity extends Activity {

    private TextView mTitle;
    private TextView mBody;
    private String mUrl;
    private LoaderManager mLoaderManager;
    private static final int LOADER_NEWS_DETAILS = 0;
    private LoaderManager.LoaderCallbacks<Cursor> mNewsDetailsLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader loader = new CursorLoader(NewsDetailsActivity.this,NewsProvider.NEWS_CONTENT_URI, Util.NEWS_LIST_PROJECTS, NewsProvider.NEWS_SOURCE + " = '" + mUrl + "'", null, null);
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            if (data.moveToFirst()) {
                String body = data.getString(data.getColumnIndex(NewsProvider.NEWS_BODY));
                if(!TextUtils.isEmpty(body)) {
                    mBody.setText(Html.fromHtml(body));
                } else {
                    mBody.setText(R.string.loading);
                    NewsSearchManager manager = new NewsSearchManager(NewsDetailsActivity.this);
                    manager.searchNewsDetails(mUrl, mHandler);
                }
                mTitle.setText(data.getString(data.getColumnIndex(NewsProvider.NEWS_TITLE)));

            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NewsSearchManager.MESSAGE_NEW_DETAILS_SEARCH_END:
                    mLoaderManager.restartLoader(LOADER_NEWS_DETAILS, null, mNewsDetailsLoaderCallbacks);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details);
        mTitle = (TextView) findViewById(R.id.news_details_title);
        mBody = (TextView) findViewById(R.id.news_details_body);
        mUrl = getIntent().getStringExtra(NewsProvider.NEWS_SOURCE);
        mLoaderManager = getLoaderManager();
        mLoaderManager.initLoader(LOADER_NEWS_DETAILS, null, mNewsDetailsLoaderCallbacks);
    }
}
