package com.ysq.happens.ui;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.ysq.happens.R;
import com.ysq.happens.database.NewsProvider;
import com.ysq.happens.transition.NewsSearchManager;
import com.ysq.happens.transition.NewsSearchThread;

import org.jsoup.nodes.Document;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ListFragment extends Fragment implements NewsSearchThread.NewsSearchThreadEndListener {
    private static final String TAG = "ListFragment";
    private LoaderManager mLoadermanager;
    private ListView mListView;
    private NewsListAdapter mListAdapter;
    private static final int LOADER_NEWS_LIST = 0;
    private static final String[] NEWS_LIST_PROJECTS = {
            "_id",
            NewsProvider.NEWS_TITLE
    };

    private LoaderManager.LoaderCallbacks<Cursor> mNewsListLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            CursorLoader loader = new CursorLoader(getActivity(),NewsProvider.NEWS_CONTENT_URI, NEWS_LIST_PROJECTS, null, null, null);
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            mListAdapter.swapCursor(data);
        }


        @Override
        public void onLoaderReset(Loader loader) {
            mListAdapter.swapCursor(null);
        }
    };
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mLoadermanager.restartLoader(LOADER_NEWS_LIST, null, mNewsListLoaderCallbacks);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLoadermanager = getLoaderManager();
        mLoadermanager.initLoader(LOADER_NEWS_LIST, null, mNewsListLoaderCallbacks);
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        mListView = (ListView) view.findViewById(R.id.news_list);
        mListAdapter = new NewsListAdapter(getActivity(), null, false);
        mListView.setAdapter(mListAdapter);
        NewsSearchManager manager = new NewsSearchManager(getActivity());
        manager.searchNeteaseNewsList("http://news.163.com/", mHandler);
        return view;
    }

    @Override
    public void onSearchThreadEnd(Document document) {
    }
}
