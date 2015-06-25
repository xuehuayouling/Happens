package com.ysq.happens.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import com.ysq.happens.database.DatebaseHelper;

/**
 * Created by Administrator on 2015/6/25.
 */
public class NewsProvider extends ContentProvider {
    public static final String NEWS_TITLE = "title";
    public static final String NEWS_OVERVIEW = "overview";
    public static final String NEWS_BODY = "body";
    public static final String NEWS_DATE = "date";
    public static final String NEWS_SOURCE = "source";
    public static final String NEWS_AUTHOR = "author";
    private DatebaseHelper mDatebaseHelper;
    @Override
    public boolean onCreate() {
        mDatebaseHelper = new DatebaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
