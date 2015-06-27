package com.ysq.happens;

import android.database.Cursor;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ysq.happens.database.NewsProvider;
import com.ysq.happens.transition.NewsSearchManager;
import com.ysq.happens.transition.NewsSearchThread;

import org.jsoup.nodes.Document;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements NewsSearchThread.NewsSearchThreadEndListener {
    private static final String TAG = "MainActivityFragment";
    private TextView mTextView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String[] select = {
              NewsProvider.NEWS_TITLE, NewsProvider.NEWS_SOURCE
            };
            Cursor cursor = null;
            try {
                cursor = getActivity().getContentResolver().query(NewsProvider.NEWS_CONTENT_URI, select, null, null, null);
                Log.d(TAG, "" + cursor.getCount());
                while (cursor.moveToNext()) {
                    Log.d(TAG, cursor.getString(0) + "\t" + cursor.getString(1) + "\n");
                    mTextView.setText(mTextView.getText() + cursor.getString(0) + "\t" + cursor.getString(1) + "\n");
                }
            } finally {
                if(cursor != null) {
                    cursor.close();
                    cursor = null;
                }
            }

        }
    };
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        NewsSearchManager manager = new NewsSearchManager(getActivity());
        manager.searchNeteaseNewsList("http://news.163.com/", mHandler);
        mTextView = (TextView) view.findViewById(R.id.text);
        return view;
    }

    @Override
    public void onSearchThreadEnd(Document document) {
        mTextView.setText(document.data());
    }
}
