package com.ysq.happens.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ysq.happens.R;
import com.ysq.happens.database.NewsProvider;

/**
 * Created by Administrator on 2015/6/28.
 */
public class NewsListAdapter extends CursorAdapter{
    public NewsListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout) vi.inflate(R.layout.news_item, parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = (TextView) view.findViewById(R.id.item_title);
        if (cursor != null) {
            title.setText(cursor.getString(cursor.getColumnIndex(NewsProvider.NEWS_TITLE)));
        }
    }
}
