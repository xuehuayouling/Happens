package com.ysq.happens.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/6/25.
 */
public class DatebaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String LOCAL_DB_NAME = "news.db";
    private static final String NEWS_TB_NAME = "news";
    private Context mContext;

    public DatebaseHelper(Context context) {
        super(context, LOCAL_DB_NAME, null, DB_VERSION);
        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + NEWS_TB_NAME + "(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NewsProvider.NEWS_TITLE + " TEXT, " +
                NewsProvider.NEWS_OVERVIEW + " TEXT, " +
                NewsProvider.NEWS_BODY + " TEXT, " +
                NewsProvider.NEWS_DATE + " INTEGER, " +
                NewsProvider.NEWS_SOURCE + " TEXT, " +
                NewsProvider.NEWS_AUTHOR + " TEXT, " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NEWS_TB_NAME);
        onCreate(db);
    }
}
